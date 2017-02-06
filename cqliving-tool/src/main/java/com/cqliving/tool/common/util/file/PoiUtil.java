package com.cqliving.tool.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PoiUtil {

	public static HSSFWorkbook createWorkbook(String sheetName, Map<String, String> title,
			List<Map<String, Object>> result) throws IllegalArgumentException, IllegalAccessException,
			InstantiationException {
		HSSFWorkbook hwb = new HSSFWorkbook();
		createSheet(hwb, sheetName, title, result);
		return hwb;
	}

	public static void createSheet(HSSFWorkbook book, String sheetName, Map<String, String> title,
			List<Map<String, Object>> result) throws IllegalArgumentException, IllegalAccessException,
			InstantiationException {
		HSSFSheet sheet = book.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 350);
		HSSFCellStyle titleCellStyle = (HSSFCellStyle) createTitleCellStyle(book);
		/* 添加标题行 */
		int num = 0;
		for (String t : title.keySet()) {
			sheet.setColumnWidth(num, 5000);
			HSSFCell cell = row.createCell(num);
			cell.setCellStyle(titleCellStyle);
			setCellValue(cell, t);
			++num;
		}

		addDatagridToExcel(book, sheet, title.values(), result);
	}

	private static void addDatagridToExcel(HSSFWorkbook book, HSSFSheet sheet, Collection<String> titles,
			List<Map<String, Object>> result) {
		if (result == null || result.isEmpty()) {
			return;
		}

		/* 从第一行开始生成数据 */
		HSSFRow row = null;
		HSSFCell cell = null;
		Map<String, Object> map = null;
		HSSFCellStyle style = (HSSFCellStyle) createCellStyle(book);
		for (int i = 0; i < result.size(); i++) {
			row = sheet.createRow(i + 1);
			map = result.get(i);
			int index = 0;
			for (String v : titles) {
				cell = row.createCell(index);
				cell.setCellStyle(style);
				setCellValue(cell, map.get(v));
				++index;
			}
		}
	}

	private static CellStyle createCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		Font titleFont = wb.createFont();
		titleFont.setFontName("宋体");
		titleFont.setBoldweight((short) 13);
		style.setFont(titleFont);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}

	private static CellStyle createTitleCellStyle(HSSFWorkbook wb) {
		CellStyle style = createCellStyle(wb);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		return style;
	}

	/**
	 * 设置Excel样式和值
	 * 
	 * @param cell
	 * @param value
	 */
	private static void setCellValue(Cell cell, Object value) {
		if (value == null) {
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			cell.setCellValue("");
		} else if (value instanceof Short || value.getClass() == short.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Short) value);
		} else if (value instanceof Integer || value.getClass() == int.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long || value.getClass() == long.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Long) value);
		} else if (value instanceof Float || value.getClass() == float.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Float) value);
		} else if (value instanceof Double || value.getClass() == double.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Double) value);
		} else if (value instanceof Date) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(DateUtil.format((Date) value, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
		} else {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue((String) value);
		}
	}

	public static void main(String[] args) throws Exception {
		// Map<String, String> title = Maps.newLinkedHashMap();
		// title.put("测试1", "aaaaa");
		// title.put("测试2", "bbbb");
		// title.put("测试3", "cccc");
		// List<Map<String, Object>> r = Lists.newArrayList();
		// Date d = new Date();
		// for (int i = 0; i < 30; i++) {
		// Map<String, Object> m = Maps.newHashMap();
		// m.put("aaaaa", "发达省份罗拉克斯地方");
		// m.put("bbbb", i);
		// m.put("cccc", org.apache.commons.lang3.time.DateUtils.addDays(d, i +
		// 1));
		// r.add(m);
		// }
		// FileOutputStream stream = new FileOutputStream(new
		// File("e:/abc.xls"));
		// HSSFWorkbook book = createWorkbook("test", title, null);
		// book.write(stream);
		// stream.close();
		// FileInputStream steam = new FileInputStream(new File("e:/abc.xls"));
		// List<Map<String, Object>> r = Lists.newArrayList();
		// importExcel(steam, title, r);
		// for (Object o : r) {
		// System.out.println(o);
		// }
		// steam.close();

		Map<String, String> title = Maps.newTreeMap();
		title.put("题目标题（*）", "title");
		title.put("题目内容（*）", "content");
		title.put("题目类型（*）", "type");
		title.put("答案明细（*）", "detail");
		title.put("答案分组（*）", "group");
		title.put("答案分数（*）", "score");
		title.put("是否为正确答案", "isRight");

		InputStream input = new FileInputStream(new File("e:/evaluating.xls"));
		List<Map<String, Object>> result = Lists.newArrayList();
		importExcel(input, "样例示例", 0, title, result);

	}

	static final DecimalFormat DECIMALFORMAT = new DecimalFormat("#");

	/**
	 * 判断解析Cell数据类型并进行转换
	 * 
	 * @param cell
	 * @return ret
	 */
	private static Object getExcelCellValue(Cell cell) throws Exception {
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING: {
			return cell.getStringCellValue();
		}
		case HSSFCell.CELL_TYPE_NUMERIC: {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				String value = DECIMALFORMAT.format(cell.getNumericCellValue());
				Double dValue = Double.valueOf(value);
				if (dValue > Integer.MIN_VALUE && dValue < Integer.MAX_VALUE) {
					return dValue.intValue();
				} else if (dValue > Long.MIN_VALUE && dValue < Long.MAX_VALUE) {
					return dValue.longValue();
				} else {
					return dValue;
				}
			}
		}
		case HSSFCell.CELL_TYPE_FORMULA: {
			return cell.getCellFormula();
		}
		case HSSFCell.CELL_TYPE_ERROR: {
			return String.valueOf(cell.getErrorCellValue());
		}
		case HSSFCell.CELL_TYPE_BOOLEAN: {
			return String.valueOf(cell.getBooleanCellValue());
		}
		default:
			throw new BusinessException("未找到匹配类型！");
		}
	}

	public static void importExcel(InputStream input, String excelName, int titleRow, Map<String, String> title,
			List<Map<String, Object>> result) throws BusinessException {
		try {
			HSSFWorkbook hwb = new HSSFWorkbook(input);
			getSheet(hwb, excelName, titleRow, title, result);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				throw new BusinessException("关闭流失败", e);
			}
		}
	}

	public static void importExcel(InputStream input, Map<String, String> title, List<Map<String, Object>> result)
			throws BusinessException {
		importExcel(input, null, 0, title, result);
	}

	public static void getSheet(HSSFWorkbook book, String sheetName, int titleRow, Map<String, String> title,
			List<Map<String, Object>> result) throws IllegalArgumentException, IllegalAccessException,
			InstantiationException, Exception {
		HSSFSheet sheet = null;
		if (StringUtils.isNotBlank(sheetName)) {
			sheet = book.getSheet(sheetName);
		} else {
			sheet = book.getSheetAt(0);
		}

		HSSFRow row = sheet.getRow(titleRow);
		Cell cell = null;
		List<String> keys = Lists.newArrayList();
		for (Iterator<Cell> it = row.cellIterator(); it.hasNext();) {
			cell = it.next();
			String t = (String) getExcelCellValue(cell);
			if (StringUtils.isNotBlank(t)) {
				t = t.trim();
			}
			String v = title.get(t);
			if (StringUtils.isBlank(v)) {
				throw new IOException("字段：“" + t + "”在模板中未定义！");
			}
			keys.add(v);
		}

		int rowSize = sheet.getPhysicalNumberOfRows();
		int cellSize = 0;
		Map<String, Object> m = null;
		for (int i = titleRow + 1; i < rowSize; i++) {
			row = sheet.getRow(i);
			cellSize = row.getPhysicalNumberOfCells();
			m = Maps.newHashMapWithExpectedSize(cellSize);
			for (int j = 0; j < cellSize; j++) {
				cell = row.getCell(j);
				m.put(keys.get(j), getExcelCellValue(cell));
			}
			result.add(m);
		}
	}

	public static void exportExcel(HttpServletResponse response, String excelName, Map<String, String> titleRow,
			List<Map<String, Object>> result) throws BusinessException {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			Workbook book = createWorkbook(excelName, titleRow, result);
			response.reset();
			response.setContentType("bin");
			String fileName = new String(excelName.getBytes("gb2312"), "iso8859-1");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls\"");
			book.write(os);
			os.flush();
		} catch (Exception e) {
			throw new BusinessException("exportExcel method error:" + e.getMessage());
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					throw new BusinessException("exportExcel method error:" + e.getMessage());
				}
			}

		}
	}
	/**
	 * 
	 * getStringCellValue:获取单元格string类型值. <p> 
	 * 
	 * 
	 * @author weiming 
	 * @param cell
	 * @return
	 */
	public static String getStringCellValue(Cell cell){
		return cell==null?null:cell.getStringCellValue();
	}
	/**
	 * 
	 * getStringCellValue:获取单元格string类型值. <p> 
	 * 
	 * @author weiming 
	 * @param row
	 * @param cellIndex
	 * @return
	 */
	public static String  getStringCellValue(Row row,int cellIndex){
		return row==null?null:getStringCellValue(row.getCell(cellIndex));
	}
}
