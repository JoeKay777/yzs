package cn.com.seo.base.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ICell;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class ReadExcel {
	/** 
	 * 适用于第一行是标题行的excel，例如 
	 * 姓名   年龄  性别  身高 
	 * 张三   25  男   175 
	 * 李四   22  女   160 
	 * 每一行构成一个map，key值是列标题，value是列值。没有值的单元格其value值为null 
	 * 返回结果最外层的list对应一个excel文件，第二层的list对应一个sheet页，第三层的map对应sheet页中的一行 
	 * @throws Exception  
	 */  
	public static List<List<Map<String, String>>> readExcelWithTitle(String filepath) throws Exception{  
	    String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());  
	    InputStream is = null;  
	    Workbook wb = null;  
	    try {  
	        is = new FileInputStream(filepath);  
	          
	        if (fileType.equals("xls")) {  
	            wb = new HSSFWorkbook(is);  
	        } else if (fileType.equals("xlsx")) {  
	            wb = new XSSFWorkbook(is);  
	        } else {  
	            throw new Exception("读取的不是excel文件");  
	        }  
	          
	        List<List<Map<String, String>>> result = new ArrayList<List<Map<String,String>>>();//对应excel文件  
	          
	        int sheetSize = wb.getNumberOfSheets();  
	        for (int i = 0; i < sheetSize; i++) {//遍历sheet页  
	            Sheet sheet = wb.getSheetAt(i);  
	            List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//对应sheet页  
	              
	            List<String> titles = new ArrayList<String>();//放置所有的标题  
	              
	            int rowSize = sheet.getLastRowNum() + 1;  
	            for (int j = 0; j < rowSize; j++) {//遍历行  
	                Row row = sheet.getRow(j);  
	                if (row == null) {//略过空行  
	                    continue;  
	                }  
	                int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列  
	                if (j == 0) {//第一行是标题行  
	                    for (int k = 0; k < cellSize; k++) {  
	                        Cell cell = row.getCell(k);  
	                        titles.add(cell.toString());  
	                    }  
	                } else {//其他行是数据行  
	                    Map<String, String> rowMap = new HashMap<String, String>();//对应一个数据行  
	                    for (int k = 0; k < titles.size(); k++) {  
	                        Cell cell = row.getCell(k);  
	                        if(cell==null){
	                        	continue;
	                        }
	                        String key = titles.get(k);  
	                        String value=getCellType(cell);
	                        rowMap.put(key, value);  
	                    }  
	                    sheetList.add(rowMap);  
	                }  
	            }  
	            result.add(sheetList);  
	        }  
	          
	        return result;  
	    } catch (FileNotFoundException e) {  
	        throw e;  
	    } finally {  
	        if (wb != null) {  
	            wb.close();  
	        }  
	        if (is != null) {  
	            is.close();  
	        }  
	    }  
	}  
	
	
	public static String getCellType(Cell cell){
		String value=null;
		switch (cell.getCellType()) {
	      case HSSFCell.CELL_TYPE_NUMERIC: // 数字
	          //如果为时间格式的内容
	          if (HSSFDateUtil.isCellDateFormatted(cell)) {      
	             //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	             value=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();                                 
	               break;
	           } else {
	               value = new DecimalFormat("0").format(cell.getNumericCellValue());
	           }
	          break;
	      case HSSFCell.CELL_TYPE_STRING: // 字符串
	          value = cell.getStringCellValue();
	          break;
	      case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
	          value = cell.getBooleanCellValue() + "";
	          break;
	      case HSSFCell.CELL_TYPE_FORMULA: // 公式
	          value = cell.getCellFormula() + "";
	          break;
	      case HSSFCell.CELL_TYPE_BLANK: // 空值
	          value = "";
	          break;
	      case HSSFCell.CELL_TYPE_ERROR: // 故障
	          value = "非法字符";
	          break;
	      default:
	          value = "未知类型";
	          break;
		}
		return value;
	}

  
}
