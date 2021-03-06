package com.cqliving.framework.common.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.cqliving.framework.common.service.EntityService;
import com.cqliving.framework.utils.Dates;
import com.cqliving.framework.utils.Reflections;
import com.cqliving.framework.utils.mapper.CsvMapper;
import com.google.common.collect.Lists;

/**
 * 文件上传下载，数据导入导出操作封装
 * 
 * @author zhangpu
 * 
 * @param <T>
 * @param <M>
 */
public abstract class AbstractFileOperationController<T extends AbstractEntity, M extends EntityService<T>>
		extends AbstractOperationController<T, M> {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractFileOperationController.class);

	public static enum FileType {
		EXCEL, CSV
	}

	/** 文件上传默认配置 */
	protected UploadConfig uploadConfig = new UploadConfig();

	protected List<T> doImportExcel(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		return doImport(request, response, model, FileType.EXCEL);
	}

	protected List<T> doImportCsv(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return doImport(request, response, model, FileType.CSV);
	}

	/**
	 * 批量导入保存模板数据基础实现
	 * 
	 * 该方法用于子类实际的导入Action方法实现完整的数据导入框架功能，子类需要补充：错误处理，消息处理，选择返回视图模式（Json还是页面跳转）
	 * 最简使用方法：子类中实现每行的反序列化：unmarshal(List<String[]> lines)<br>
	 * <p>
	 * 功能:
	 * <li>自动完成文件上传，参考：doUpload(HttpServletRequest request)
	 * <li>根据上次文件的类型，实现文件（行）数据到普通数组的转换（一行文件数据对应一个数组，每列对应数组中一个成员）,参考：
	 * doImportLoadXls和doImportLoadCsv
	 * <li>数据组装(数组到实体对象)前的合法性检查回调, 参考：beforeUnmarshal
	 * <li>只需要实现每行数据的反序列化，自动反序列化数组数据集到实体集合，参考：unmarshal
	 * <li>支持转换为实体集合，批量保存前进行合法性检查和相关类处理：afterUnmarshal
	 * <li>最后调用服务层批量保存
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param fileType
	 * @return
	 * @throws Exception
	 */
	protected List<T> doImport(HttpServletRequest request,
			HttpServletResponse response, Model model, FileType fileType)
			throws Exception {
		Map<String, UploadResult> uploadResults = doUpload(request);

		List<String[]> lines = null;
		if (fileType == FileType.EXCEL) {
			lines = doImportLoadXls(uploadResults, getCharsetName(request));
		} else {
			lines = doImportLoadCsv(uploadResults, getCharsetName(request));
		}
		beforeUnmarshal(lines);
		List<T> entities = unmarshal(lines);
		afterUnmarshal(entities);
		getEntityService().saves(entities);
		return entities;
	}

	protected List<T> doImport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, UploadResult> uploadResults = doUpload(request);
		List<String[]> lines = doImportLoad(uploadResults,
				getCharsetName(request));
		beforeUnmarshal(lines);
		List<T> entities = unmarshal(lines);
		afterUnmarshal(entities);
		getEntityService().saves(entities);
		return entities;
	}

	protected List<String[]> doImportLoad(
			Map<String, UploadResult> uploadResults, String encoding) {

		if (uploadResults == null || uploadResults.size() == 0) {
			return null;
		}
		FileType fileType = getFileType(uploadResults.entrySet().iterator()
				.next().getValue());
		if (fileType == FileType.EXCEL) {
			return doImportLoadXls(uploadResults, encoding);
		} else if (fileType == FileType.CSV) {
			return doImportLoadCsv(uploadResults, encoding);
		} else {
			return null;
		}
	}

	private FileType getFileType(UploadResult uploadResult) {
		String ext = getFileExtention(uploadResult.getName());
		if (StringUtils.equalsIgnoreCase(ext, "xls")) {
			return FileType.EXCEL;
		} else if (StringUtils.equalsIgnoreCase(ext, "csv")) {
			return FileType.CSV;
		} else {
			return null;
		}
	}

	/**
	 * 从上传文件中读取数据到集合中
	 * 
	 * @param uploadResults
	 * @return
	 */
	protected List<String[]> doImportLoadXls(
			Map<String, UploadResult> uploadResults, String charset) {
		if (uploadResults == null || uploadResults.size() == 0) {
			logger.debug("No files have been uploaded successfully.");
			throw new RuntimeException("没有文件上传成功");
		}
		List<String[]> lines = new LinkedList<String[]>();
		for (Map.Entry<String, UploadResult> entry : uploadResults.entrySet()) {
			UploadResult uResult = entry.getValue();
			int readRows = 0;
			InputStream in = null;
			Workbook workBook = null;
			try {
				if (uploadConfig.isUseMemery()) {
					in = uResult.getInputStream();
				} else {
					File file = uResult.getFile();
					in = new FileInputStream(file);
				}
				workBook = Workbook.getWorkbook(in);
				Sheet sheet = workBook.getSheet(0);
				Cell cell = null;
				List<String> row = null;
				for (int j = 0; j < sheet.getRows(); j++) {
					row = new ArrayList<String>(sheet.getColumns());
					for (int i = 0; i < sheet.getColumns(); i++) {
						cell = sheet.getCell(i, j);
						row.add(cell.getContents());
					}
					lines.add(row.toArray(new String[] {}));
				}
			} catch (Exception e) {
				logger.debug("Read line[" + readRows
						+ "] failure with stream mode on file["
						+ uResult.getName() + "]" + e.getMessage());
				throw new RuntimeException("读取文件[" + uResult.getName()
						+ "]行错误,行号:" + readRows + " ,原因:" + e.getMessage());
			} finally {
				IOUtils.closeQuietly(in);
				if (workBook != null) {
					workBook.close();
				}
				if (!uploadConfig.isUseMemery()) {
					if (uResult.getFile().exists()) {
						uResult.getFile().delete();
					}
				}
			}
		}
		return lines;
	}

	/**
	 * 从上传文件中读取数据到集合中
	 * 
	 * @param uploadResults
	 * @return
	 */
	protected List<String[]> doImportLoadCsv(
			Map<String, UploadResult> uploadResults, String charset) {
		if (uploadResults == null || uploadResults.size() == 0) {
			logger.debug("No files have been uploaded successfully.");
			throw new RuntimeException("没有文件上传成功");
		}
		List<String[]> lines = new LinkedList<String[]>();
		for (Map.Entry<String, UploadResult> entry : uploadResults.entrySet()) {
			UploadResult uResult = entry.getValue();
			int readRows = 0;
			BufferedReader reader = null;
			try {
				if (uploadConfig.isUseMemery()) {
					reader = new BufferedReader(new InputStreamReader(
							uResult.getInputStream(), charset));
				} else {
					File file = uResult.getFile();
					reader = new BufferedReader(new InputStreamReader(
							new FileInputStream(file), charset));
				}
				String line = null;
				while ((line = reader.readLine()) != null) {
					readRows++;
					List<String> fieldData = CsvMapper.unmarshal(line);
					String[] fields = fieldData.toArray(new String[] {});
					lines.add(fields);
				}
			} catch (Exception e) {
				logger.debug("Read line[" + readRows
						+ "] failure with stream mode on file["
						+ uResult.getName() + "]" + e.getMessage());
				throw new RuntimeException("读取文件[" + uResult.getName()
						+ "]行错误,行号:" + readRows);
			} finally {
				IOUtils.closeQuietly(reader);
				if (!uploadConfig.isUseMemery()) {
					if (uResult.getFile().exists()) {
						uResult.getFile().delete();
					}
				}
			}
		}
		return lines;
	}

	/**
	 * 读取文件后，转换为主实体对象前，进行预处理。可选：参数检测；主实体对象相关的子对象的导入处理等。 <br>
	 * 可选这里可以进行合法性检测，剔除格式错误的行，返回正确格式的行
	 * ，然后在Message中记录错误的提示。如果选择图略错误进行保存正确格式的数据，则不抛出异常，否则抛出异常，终止批量保存。 <br>
	 * 默认实现是返回传入的集合，不做任何处理
	 * 
	 * @param lines
	 */
	protected List<String[]> beforeUnmarshal(List<String[]> lines) {
		return lines;
	}

	/**
	 * 转换读取的数据为实体
	 * 
	 * @param uploadResults
	 */
	protected List<T> unmarshal(List<String[]> lines) {
		List<T> entities = new LinkedList<T>();
		for (String[] line : lines) {
			entities.add(doUnmarshalEntity(line));
		}
		return entities;
	}

	/**
	 * 转换为主对象后，批量保存前，对象预处理，可选：参数检测和相关对象处理
	 * 
	 * @param contents
	 */
	protected void afterUnmarshal(List<T> entities) {

	}

	/**
	 * 转换读取的行为实体对象
	 * 
	 * @param fields
	 * @return
	 */
	protected T doUnmarshalEntity(String[] fields) {
		return null;
	}

	/**
	 * 导出Excel模板方法
	 * 
	 * 本模板方法提供EXCEL导出的程序框架，子类需要实现doExportXls：根据业务要求，编码选择输出操作内容和方式。
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	protected void doExportExcel(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		List<T> list = doQuery(request, response, model);
		doExportExcelHeader(request, response);
		doExportExcelBody(list, request, response);
	}

	/**
	 * 导出CSV操作
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	protected void doExportCsv(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		List<T> list = doQuery(request, response, model);
		doExportCsvHeader(request, response);
		doExportCsvBody(list, request, response);
	}

	/**
	 * 设置导出Excel文件的Http头，只要是设置下载文件的文件名和MITA信息
	 */
	protected void doExportExcelHeader(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = getExportFileName(request);
		try {
			fileName = URLEncoder.encode(fileName, getCharsetName(request));
		} catch (Exception e) {
			// ig
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment");
		response.setHeader("Content-Disposition", "filename=\"" + fileName
				+ ".xls\"");
	}

	/**
	 * 导出Excel实现。 <br>
	 * <li>可选使用jxl,poi方式直接输出流到reponse,不需要导出页面。该方法返回fase; <li>
	 * 可选使用页面方式实现，需要返回到导出页面。该方法返回true
	 * 
	 * @param list
	 * @param request
	 * @param response
	 * @return
	 */
	protected boolean doExportExcelBody(List<T> list,
			HttpServletRequest request, HttpServletResponse response) {
		WritableWorkbook workbook = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			String[] headerNames = getExportTitles();
			workbook = Workbook.createWorkbook(out);
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			int row = 0;
			// 写入header
			if (headerNames != null) {
				for (int i = 0; i < headerNames.length; i++) {
					sheet.addCell(new Label(i, row, headerNames[i]));
				}
				row++;
			}

			for (T entity : list) {
				List<String> entityData = doMarshalEntityToXls(entity);
				for (int i = 0; i < entityData.size(); i++) {
					sheet.addCell(new Label(i, row, entityData.get(i)));
				}
				row++;
			}
			workbook.write();
			out.flush();
		} catch (Exception e) {
			logger.warn("do export excel failure -> " + e.getMessage(), e);
			throw new RuntimeException("执行导出过程失败[" + e.getMessage() + "]");
		} finally {
			try {
				workbook.close();
			} catch (Exception e2) {
				// ig
			}
			IOUtils.closeQuietly(out);
		}
		return false;
	}

	/**
	 * 设置导出Excel文件的Http头，只要是设置下载文件的文件名和MITA信息
	 */
	protected void doExportCsvHeader(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = getExportFileName(request);
		try {
			fileName = URLEncoder.encode(fileName, getCharsetName(request));
		} catch (Exception e) {
			// ig
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ fileName + ".csv\"");
	}

	/**
	 * 实现导出输出的程序模板，子类可以直接使用或根据自己的需求覆写
	 * 
	 * @param list
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void doExportCsvBody(List<T> list, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter p = null;
		try {
			p = new PrintWriter(new OutputStreamWriter(
					response.getOutputStream(), getCharsetName(request)));
			String[] headers = getExportTitles();
			if (headers != null) {
				p.println(CsvMapper.marshal(Lists.newArrayList(headers)));
				p.flush();
			}
			int flushThreshold = 1000;
			int sendCount = 0;
			for (T entity : list) {
				p.println(CsvMapper.marshal(doMarshalEntityToCsv(entity)));
				sendCount++;
				if (sendCount % flushThreshold == 0) {
					p.flush();
				}
			}
			p.flush();
			p.close();
		} catch (Exception e) {
			logger.warn("do export csv failure -> " + e.getMessage(), e);
			throw new RuntimeException("CSV输出失败[" + e.getMessage() + "]");
		} finally {
			IOUtils.closeQuietly(p);
		}
	}

	/**
	 * 编列实体对象为List<String>待输出格式
	 * 
	 * 这里为简化操作，要求改方法内完成属性的类型转换为输出String类型。如果需要对字段类型进行精确控制，可以考虑复写doExportXls方法实现
	 * 
	 * @param entity
	 */
	protected List<String> doMarshalEntityToXls(T entity) {
		Set<String> simplePropertyNames = Reflections
				.getSimpleFieldNames(entity.getClass());
		String[] propertyNames = simplePropertyNames.toArray(new String[] {});
		return Reflections.invokeGetterToString(entity, propertyNames);
	}

	/**
	 * 编列实体对象为CSV格式
	 * 
	 * @param entity
	 */
	protected List<String> doMarshalEntityToCsv(T entity) {
		Set<String> simplePropertyNames = Reflections
				.getSimpleFieldNames(entity.getClass());
		String[] propertyNames = simplePropertyNames.toArray(new String[] {});
		return Reflections.invokeGetterToString(entity, propertyNames);
	}

	/**
	 * 返回导出文件内容的标题行，默认为空，不导出标题行
	 * 
	 * @return
	 */
	protected String[] getExportTitles() {
		return null;
	}

	/**
	 * 导出文件的文件名
	 * 
	 * @return
	 */
	protected String getExportFileName(HttpServletRequest request) {
		String fileName = getEntityName();
		String exportFileName = request.getParameter("exportFileName");
		if (StringUtils.isNotBlank(exportFileName)) {
			fileName = exportFileName;
		}
		return fileName;
	}

	/**
	 * 上传处理
	 * 
	 * @param request
	 * @return key --> 请求的表单参数名称, value ---> 上传结果
	 */
	protected Map<String, UploadResult> doUpload(HttpServletRequest request)
			throws Exception {
		UploadConfig uploadConfig = getUploadConfig();
		Map<String, UploadResult> uploadResults = new HashMap<String, UploadResult>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFiles = multiRequest.getFileMap();
		UploadResult result = null;
		for (Map.Entry<String, MultipartFile> entry : multipartFiles.entrySet()) {
			MultipartFile mfile = entry.getValue();
			String filename = mfile.getOriginalFilename();
			if (mfile.getSize() > uploadConfig.getMaxSize()) {
				throw new RuntimeException("文件[" + filename + "]大小操作限制，最大限制:"
						+ uploadConfig.getMaxSize() / 1024 / 1024 + "M");
			}
			String fileExtention = getFileExtention(filename);
			if (!StringUtils.containsIgnoreCase(
					uploadConfig.getAllowExtentions(), fileExtention)) {
				throw new RuntimeException("文件[" + filename + "]类型不支持，支持类型:"
						+ uploadConfig.getAllowExtentions());
			}

			if (uploadConfig.isUseMemery()) {
				// 内存方式，不转存到服务器存储，直接返回流给调用端
				result = new UploadResult(entry.getKey(), filename,
						mfile.getSize(), mfile.getInputStream());
			} else {
				// 转存到服务器，返回服务器文件
				File destFile = new File(getUploadFileName(filename,
						uploadConfig));
				mfile.transferTo(destFile);
				result = new UploadResult(entry.getKey(), filename,
						mfile.getSize(), destFile);
			}
			uploadResults.put(entry.getKey(), result);
		}
		return uploadResults;
	}

	protected String getUploadFileName(String originalFileName,
			UploadConfig uploadConfig) {
		String fileName = originalFileName;
		if (uploadConfig.isNeedRemaneToTimestamp()) {
			fileName = buildUploadFileName() + "." + getFileExtention(fileName);
		}
		return buildUploadStoragePath(uploadConfig.getStorageRoot(),
				uploadConfig.isNeedTimePartPath(), null)
				+ File.pathSeparator
				+ fileName;
	}

	protected String buildUploadFileName() {
		return Dates.format(new Date(), Dates.DATETIME_NOT_SEPARATOR)
				+ RandomStringUtils.randomNumeric(4);
	}

	protected String buildUploadStoragePath(String storageRoot,
			boolean needTimePartPath, Date baseDate) {
		String path = storageRoot;
		try {
			if (needTimePartPath) {
				Date d = baseDate;
				if (d == null) {
					d = new Date();
				}
				String pathTimestamp = Dates.format(d,
						Dates.DATETIME_NOT_SEPARATOR);
				String yearPart = StringUtils.left(pathTimestamp, 4);
				String monthPart = StringUtils.substring(pathTimestamp, 4, 6);
				String dayPart = StringUtils.substring(pathTimestamp, 6, 8);
				String subPath = File.pathSeparator + yearPart
						+ File.pathSeparator + monthPart + File.pathSeparator
						+ dayPart;

				path += subPath;
			}
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
		} catch (Exception e) {
			logger.warn("Build path failure --> " + e.getMessage());
		}
		return path;

	}

	protected UploadConfig getUploadConfig() {
		return this.uploadConfig;
	}

	protected void setUploadConfig(UploadConfig uploadConfig) {
		this.uploadConfig = uploadConfig;
	}

	private String getFileExtention(String filePath) {
		return StringUtils.substringAfterLast(filePath, ".");
	}

	private String getCharsetName(HttpServletRequest request) {
		String charset = "UTF-8";
		String requestCharset = StringUtils.trimToEmpty(request
				.getParameter("charset"));
		if (StringUtils.isNotBlank(requestCharset)) {
			charset = requestCharset;
		}
		return charset;
	}

	protected static class UploadResult {
		private String name;
		private long size;
		private File file;
		private String parameterName;
		private InputStream inputStream;

		public UploadResult() {
			super();
		}

		public UploadResult(String parameterName, String name, long size,
				File file) {
			super();
			this.name = name;
			this.size = size;
			this.file = file;
			this.parameterName = parameterName;
		}

		public UploadResult(String parameterName, String name, long size,
				InputStream inputStream) {
			super();
			this.name = name;
			this.size = size;
			this.parameterName = parameterName;
			this.inputStream = inputStream;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getSize() {
			return size;
		}

		public void setSize(long size) {
			this.size = size;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public String getParameterName() {
			return parameterName;
		}

		public void setParameterName(String parameterName) {
			this.parameterName = parameterName;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

	}

	protected static class UploadConfig {
		private long maxSize = 1024 * 1024 * 32;
		private String allowExtentions = "txt,zip,csv,xls,jpg,gif,png";
		private String storageRoot = System.getenv("java.tmp");
		private boolean useMemery = true;
		private boolean needTimePartPath = true;
		private boolean needRemaneToTimestamp = true;

		public boolean isUseMemery() {
			return useMemery;
		}

		public void setUseMemery(boolean useMemery) {
			this.useMemery = useMemery;
		}

		public long getMaxSize() {
			return maxSize;
		}

		public void setMaxSize(long maxSize) {
			this.maxSize = maxSize;
		}

		public String getAllowExtentions() {
			return allowExtentions;
		}

		public void setAllowExtentions(String allowExtentions) {
			this.allowExtentions = allowExtentions;
		}

		public String getStorageRoot() {
			return storageRoot;
		}

		public void setStorageRoot(String storageRoot) {
			this.storageRoot = storageRoot;
		}

		public boolean isNeedTimePartPath() {
			return needTimePartPath;
		}

		public void setNeedTimePartPath(boolean needTimePartPath) {
			this.needTimePartPath = needTimePartPath;
		}

		public boolean isNeedRemaneToTimestamp() {
			return needRemaneToTimestamp;
		}

		public void setNeedRemaneToTimestamp(boolean needRemaneToTimestamp) {
			this.needRemaneToTimestamp = needRemaneToTimestamp;
		}
	}

}
