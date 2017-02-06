package com.cqliving.tool.common.util.file;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.utils.Reflections;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import jxl.Workbook;
import jxl.write.Blank;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.CellValue;
import com.google.common.collect.Maps;

/**
 * Excel 处理工具
 * 
 * @author zhangpu
 */
public class ExcelUtils {
    
    /** Excel 2003 */
    public static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";
    /** Excel 2007↑ */
    public static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

	public static void write(String[] headerNames, String[] propertyNames, List<Object> dtos,
			OutputStream stream) {
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(stream);
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			int row = 0;
			// 写入header
			Label label = null;
			for (int i = 0; i < headerNames.length; i++) {
				label = new Label(i, row, headerNames[i]);
				sheet.addCell(label);
			}
			// 写入数据
			row++;
			CellValue cell = null;
			for (Object instance : dtos) {
				for (int i = 0; i < propertyNames.length; i++) {
					Object cellObject = Reflections.invokeGetter(instance, propertyNames[i]);

					if (cellObject == null) {
						cell = new Blank(i, row);
					} else {
						if (cellObject.getClass().isAssignableFrom(Date.class)) {
							cell = new DateTime(i, row, (Date) cellObject);
						} else if (cellObject.getClass().isAssignableFrom(Double.class)) {
							cell = new jxl.write.Number(i, row, (Double) cellObject);
						} else if (cellObject.getClass().isAssignableFrom(Float.class)) {
							cell = new jxl.write.Number(i, row, (Float) cellObject);
						} else if (cellObject.getClass().isAssignableFrom(Long.class)) {
							cell = new jxl.write.Number(i, row, (Long) cellObject);
						} else if (cellObject.getClass().isAssignableFrom(Integer.class)) {
							cell = new jxl.write.Number(i, row, (Integer) cellObject);
						} else {
							cell = new Label(i, row, cellObject.toString());
						}
					}
					sheet.addCell(cell);
				}
				row++;
			}
			workbook.write();
		} catch (Exception e) {
			throw new RuntimeException("Write to Excel with jxl fault.", e);
		} finally {
			try {
				workbook.close();
			} catch (Exception e2) {
				// ig
			}
		}
	}

	static class PojoEntity {

		private Long id;
		private String name;
		private int type;
		private float rate;
		private double balance;
		private Date createTime;

		public PojoEntity() {
			super();
		}

		public PojoEntity(Long id, String name, int type, float rate, double balance, Date createTime) {
			super();
			this.id = id;
			this.name = name;
			this.type = type;
			this.rate = rate;
			this.balance = balance;
			this.createTime = createTime;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public float getRate() {
			return rate;
		}

		public void setRate(float rate) {
			this.rate = rate;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

	}

	public static void main(String[] args) throws Exception {
		List<String> headerNames = new ArrayList<String>();
		headerNames.add("编号");
		headerNames.add("名称");
		headerNames.add("类型");
		headerNames.add("比例");
		headerNames.add("余額");
		headerNames.add("创建时间");

		List<String> propertyNames = new ArrayList<String>();
		propertyNames.add("id");
		propertyNames.add("name");
		propertyNames.add("type");
		propertyNames.add("rate");
		propertyNames.add("balance");
		propertyNames.add("createTime");

		List<Object> entities = new ArrayList<Object>();
		for (int i = 1; i <= 10; i++) {
			entities.add(new PojoEntity(Long.valueOf(i), "name" + i, i, 0.75f, 12.3212d, new Date()));
		}

		FileOutputStream stream = new FileOutputStream("test.xls", false);
		ExcelUtils.write(headerNames.toArray(new String[]{}), propertyNames.toArray(new String[]{}), entities, stream);

		if (stream != null) {
			System.out.println("Close stream after finished excel");
			stream.close();
		}
	}
	
	/**
     * <p>Description: 根据文件流创建一个Workbook对象</p>
     * @param file excel文件流
     * @return
     * @throws Exception
     * @author huxiaoping
     */
    public static org.apache.poi.ss.usermodel.Workbook createWorkbook(MultipartFile file) throws Exception {
        if (CONTENT_TYPE_XLS.equals(file.getContentType())) {
            return new HSSFWorkbook(file.getInputStream());
        } else if (CONTENT_TYPE_XLSX.equals(file.getContentType())) {
            return new XSSFWorkbook(file.getInputStream());
        }
        logger.info("上传文件的 Content type = " + file.getContentType());
        //在 Firefox 浏览器下，会出现上传的 Excel 文件的 Content type 为 application/x-download 的问题
        //尝试构造 Workbook 对象，如果失败，提示文件类型错误
        try {
            return new HSSFWorkbook(file.getInputStream());
        } catch (Exception e) {
            try {
                return new XSSFWorkbook(file.getInputStream());
            } catch (Exception e2) {
            }
        }
        logger.info("文件类型错误：Content type = " + file.getContentType());
        throw new BusinessException("文件类型错误");
    }
    
    /**
     * 校验上传文件格式内容是否和标准模板一致
     * 
     * @param sheet
     *            Sheet对象
     * @param templateTitleList
     *            excel模板数据列表
     * @throws Exception
     *             BusinessException 1 表示文件中无数据 2 与模板标题不相符，请检查模板
     * @author huxiaoping
     */
    public static void validateExcelStandardByTemplate(Sheet sheet, List<ExcelTemplate> templateTitleList)
            throws Exception {
        int rowsNum = sheet.getPhysicalNumberOfRows();
        if (rowsNum <= 1) {
            // 1表示文件中无数据
            throw new BusinessException(-1, "文件中无数据");
        }
        // 第一行为列标题，应与对应的模板标题一致
        Row row = sheet.getRow(0);
        // 循环校验标题是否和模板一致
        Cell cell = null;
        String titleName = null;
        String templateTitleName = null;
        int columnCount = row.getPhysicalNumberOfCells();
        if (columnCount < templateTitleList.size()) {
            // 数据列与模板不符，请检查模板
            throw new BusinessException(-3, "数据列与模板不符，请检查模板");
        } else if (columnCount > templateTitleList.size()) {
            //若数据列数大于模板列数，只读取与模板列符合的列
            columnCount = templateTitleList.size();
        }
        for (int i = 0; i < columnCount; i++) {
            cell = row.getCell(i);
            // 获取用户上传的标题名称
            titleName = ExcelUtils.getExcelCellValue(cell);
            // 获取模板对应的标题名
            templateTitleName = templateTitleList.get(i).getFieldDesc();
            if (!titleName.startsWith(templateTitleName)) {
                // 与模板标题不相符，请检查模板
                throw new BusinessException(-2, "与模板标题不相符，请检查模板");
            }
        }
    }
    
    /**
     * 判断解析Cell数据类型并进行转换
     * 
     * @param cell
     * @return ret
     * @author huxiaoping
     */
    public static String getExcelCellValue(Cell cell) throws Exception {
        // 定义内部变量
        String ret = "";
        try {
            // 判断Cell是否为空
            if (cell == null) {
                return "";
            }
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:// 判断Cell类型是否为String类型
                ret = cell.getStringCellValue().trim();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                ret = NumberFormat.getNumberInstance().format(cell.getNumericCellValue());
                if (ret.indexOf(",") > -1) {
                    ret = ret.replaceAll(",", "");
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                ret = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_ERROR:
                ret = String.valueOf(cell.getErrorCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                ret = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                ret = "";
                break;
            }
        } catch (Exception ex) {
            throw new Exception("获取excel单元格数据出错");
        }
        // 返回正确的数据类型值
        return ret;
    }
    
    /**
     * 
     * Title:将excel模板转成map
     * <p>
     * Description:将excel模板转成map
     * </p>
     * 
     * @param excelTemplates
     *            excel模板对象
     * @return
     * @author huxiaoping
     */
    public static Map<String, String> transformExcelTemplateToMap(List<ExcelTemplate> excelTemplates) {
        if (ObjectUtils.isEmpty(excelTemplates))
            return null;
        Map<String, String> resultMap = Maps.newLinkedHashMap();
        for (ExcelTemplate excelTemplate : excelTemplates) {
            resultMap.put(excelTemplate.getFieldDesc(), excelTemplate.getFieldName());
        }
        return resultMap;
    }
    
    /**
     * 
     * Title:将excel每行的数据封装到实体对象中
     * <p>
     * Description:将excel每行的数据封装到实体对象中
     * </p>
     * 
     * @param clazz
     *            实体对象的class
     * @param row
     *            excel行对象
     * @return
     * @author zhuyf
     * @throws Exception
     *             BusinessException 0 无绑定的实体对象class，或者无行数据 1 无模板数据 2
     *             该行数据，有数据列为空 3 与模板类型不匹配
     */
    public static <T> T transformRowToBean(Class<T> clazz, Row row, List<ExcelTemplate> excelTemplates)
            throws Exception {
        if (clazz == null) {
            // 无绑定的实体对象class
            throw new BusinessException(-4, "无绑定的实体对象class");
        }
        if (row == null || row.getPhysicalNumberOfCells() < 1) {
            // 无行数据
            throw new BusinessException(-4, "无行数据");
        }
        if (excelTemplates == null || excelTemplates.size() < 1) {
            // 无模板数据
            throw new BusinessException(-1, "无模板数据");
        }
        // 创建对象
        T obj = clazz.getConstructor().newInstance();

        for (int i = 0; i < excelTemplates.size(); i++) {
            ExcelTemplate excelTemplate = excelTemplates.get(i);
            Field f = null;
            try {
                f = clazz.getDeclaredField(excelTemplate.getFieldName());
            } catch (Exception e) {
                // 无该字段，循环下一个
                continue;
            }

            String value = getExcelCellValue(row.getCell(i));
            Object tempValue = RegexUtils.tranHtml(value);
            //Object tempValue = value;
            //Add by Tangtao 解决不能set Integer 字段的问题
            if (f.getType().equals(Integer.class)) {
                try {
                    if (!excelTemplate.getIsBlank() && (tempValue == null || StringUtils.isBlank(tempValue.toString()))) {
                        throw new BusinessException(-3, "第"+(i+1)+"行"+excelTemplate.getFieldDesc()+"字段为空");
                    }
                    if(tempValue != null && StringUtils.isNotBlank(tempValue.toString())){
                    	tempValue = Integer.valueOf(tempValue.toString().trim());
                    }
                } catch (BusinessException e) {
                    throw new BusinessException(e.getErrorCode(), e.getMessage());
                } catch (Exception e) {
                    throw new BusinessException(-3, "数据类型错误，与模板类型不匹配");
                }
            }
            if(!ObjectUtils.isEmpty(tempValue)){
                Map<String, Object> typeMap = excelTemplate.getTypeMap();
                if (typeMap != null && !typeMap.isEmpty()) {
                    tempValue = typeMap.get(tempValue);
                    if (StringUtils.isBlank(tempValue.toString())) {
                        // 与模板类型不匹配
                        throw new BusinessException(-3, "与模板类型不匹配");
                    }
                    tempValue = Byte.valueOf(tempValue.toString().trim());
                }
                // 将值设置到对象中
                f.setAccessible(true);
                f.set(obj, tempValue);
            }
        }
        return obj;
    }
    
    /**
     * Title:根据模板对象判断是否为空
     * <p> Description:根据模板对象判断是否为空 </p>
     * @param excelTemplates excel模板对象
     * @param model 验证的对象
     * @param rowIndex 行号
     * @author YUWU on 2015年02月09日
     */
    public static void checkExcelIsBlank(List<ExcelTemplate> excelTemplates,Object model,int rowIndex) throws Exception{
        Map<Boolean,Integer> returnMap = ExcelUtils.isBlank(excelTemplates,model,rowIndex);
        if (returnMap.containsKey(Boolean.TRUE)) {
            // 无模板数据
            StringBuffer sb = new StringBuffer();
            sb.append("第").append(rowIndex + 1).append("行，第").append(returnMap.get(Boolean.TRUE) + 1).append("列内容不能为空");
            throw new BusinessException(-1, sb.toString());
        }
    }
    
    /**
     * Title:根据模板对象判断是否为空
     * <p> Description:将excel模板转成map </p>
     * @param excelTemplates excel模板对象
     * @param model 验证的对象
     * @return boolean {true:参数不能为空,false:参数可以为空}
     * @author YUWU on 2015年02月09日
     */
    public static Map<Boolean,Integer> isBlank(List<ExcelTemplate> excelTemplates,Object model,int rowIndex) throws Exception{
        //Map<String, Map<String,Object>> map = excelTemplateNameAndTypeToMap(excelTemplates);
        Map<Boolean,Integer> returnMap = new HashMap<Boolean,Integer>();
        for (int i = 0; i < excelTemplates.size(); i++) {
            ExcelTemplate excelTemplate = excelTemplates.get(i);
            Map<String, List<String>> isBlankFilterMap = excelTemplate.getIsBlankFilterMap();
            Field field1 = getField(model,excelTemplate.getFieldName());
            if(field1 == null){
                continue;
            }
            //获取当前字段的值
            String currValue = getFieldValue(model,field1);
            //1、这个字段为空。
            if(StringUtils.isBlank(currValue)){
                //2、如果这个字段可以为空。
                if(excelTemplate.getIsBlank()){
                    //3、这个字段可以为空，但是有过滤条件。
                    if(excelTemplate.getIsBlankFilterMap() != null){
                        for (Entry<String, List<String>> entry : isBlankFilterMap.entrySet()) {
                            String key = entry.getKey();
                            List<String> filterValueList = entry.getValue();
                            Field filterField = getField(model,key);
                            if(filterField == null){
                                continue;
                            }
                            //需要过滤字段的值
                            String fieldValue = getFieldValue(model,filterField);
                            boolean falg = true;
                            for (String filterValue : filterValueList) {
                                //符合为空的条件，继续下一轮
                                if(fieldValue.equals(filterValue)){
                                    falg = false;
                                    break;
                                }
                            }
                            //不符合为空的条件，则返回
                            if(falg){
                                returnMap.put(Boolean.TRUE, i);
                                return returnMap;
                            }
                        }
                    }
                }else{
                    //字段值为空，模板中配置该字段不允许为空
                    returnMap.put(Boolean.TRUE, i);
                    return returnMap;
                }
            }else{
                if(null!=excelTemplate.getMax()){
                    if(Double.valueOf(currValue.trim())>excelTemplate.getMax()){
                        throw new BusinessException(-2, "第"+(rowIndex + 1)+"行,"+excelTemplate.getFieldDesc()+"大于"+excelTemplate.getMax()+",原值："+currValue);
                    }
                }
                if(null!=excelTemplate.getMin()){
                    if(Double.valueOf(currValue.trim())<excelTemplate.getMin()){
                        throw new BusinessException(-2, "第"+(rowIndex + 1)+"行,"+excelTemplate.getFieldDesc()+"小于"+excelTemplate.getMax()+",原值："+currValue);
                    }
                }
                if(null!=excelTemplate.getMaxlength()){
                    if(currValue.length()>excelTemplate.getMaxlength()){
                        throw new BusinessException(-2, "第"+(rowIndex + 1)+"行,"+excelTemplate.getFieldDesc()+"长度大于"+excelTemplate.getMaxlength()+"字,原值："+currValue);
                    }
                }
                if(null!=excelTemplate.getMinlength()){
                    if(currValue.length()<excelTemplate.getMinlength()){
                        throw new BusinessException(-2, "第"+(rowIndex + 1)+"行,"+excelTemplate.getFieldDesc()+"长度小于"+excelTemplate.getMinlength()+"字,原值："+currValue);
                    }
                }
            }
        }
        return returnMap;
    }
    
    /**
     * 获取对象指定属性的Field
     * <p> Description:获取对象指定属性的Field </p>
     * @param model 需要获取的对象
     * @param fieldName 需要获取值的名字
     * @return String
     * @author YUWU on 2015年02月09日
     */
    public static Field getField(Object model,String fieldName) throws Exception{
        Field field = null;
        try {
            // 获取实体类的所有属性，返回Field数组
            field = model.getClass().getDeclaredField(fieldName);
        } catch (Exception e) {
            // 无该字段，继续下一个
            return null;
        }
        return field;
    }
    
    /**
     * 获取对象指定字段的值
     * <p> Description:获取对象指定字段的值 </p>
     * @param model 需要获取的对象
     * @param field 需要获取值的名字
     * @return String
     * @author YUWU on 2015年02月09日
     */
    public static String getFieldValue(Object model,Field field) throws Exception{
        String returnValue = "";
        
        // 获取属性的名字
        String name = field.getName();
        // 获取属性类型
        String type = field.getGenericType().toString();
        
        //可访问私有变量
        field.setAccessible(true);
        // 将属性的首字母大写
        name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
        Method m = model.getClass().getMethod("get" + name);
        
        // 如果type是类类型，则前面包含"class "，后面跟类名
        // 调用getter方法获取属性值
        if (type.equals("class java.lang.String")){ 
            String value = (String) m.invoke(model);
            returnValue = (value == null)?"":value;
        }else if (type.equals("class java.lang.Integer")){
            Integer value = (Integer) m.invoke(model);
            returnValue = (value == null)?"":value.toString();
        }else if (type.equals("class java.lang.Short")){
            Short value = (Short) m.invoke(model);
            returnValue = (value == null)?"":value.toString();
        }else if (type.equals("class java.lang.Double")){
            Double value = (Double) m.invoke(model);
            returnValue = (value == null)?"":value.toString();
        }else if (type.equals("class java.lang.Boolean")){
            Boolean value = (Boolean) m.invoke(model);
            returnValue = (value == null)?"":value.toString();
        }else if (type.equals("byte")){
            Byte value = (Byte) m.invoke(model);
            returnValue = (value == null)?"":value.toString();
        }else{
             Object value = m.invoke(model);
             returnValue = (value == null)?"":value.toString();
        }/*else if (type.equals("class java.util.Date")){
            Date value = (Date) m.invoke(model);
            returnValue = (value == null)?"":value.toString();
        }*/
        return returnValue;
    }
}