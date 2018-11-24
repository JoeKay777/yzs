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
	 * �����ڵ�һ���Ǳ����е�excel������ 
	 * ����   ����  �Ա�  ��� 
	 * ����   25  ��   175 
	 * ����   22  Ů   160 
	 * ÿһ�й���һ��map��keyֵ���б��⣬value����ֵ��û��ֵ�ĵ�Ԫ����valueֵΪnull 
	 * ���ؽ��������list��Ӧһ��excel�ļ����ڶ����list��Ӧһ��sheetҳ���������map��Ӧsheetҳ�е�һ�� 
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
	            throw new Exception("��ȡ�Ĳ���excel�ļ�");  
	        }  
	          
	        List<List<Map<String, String>>> result = new ArrayList<List<Map<String,String>>>();//��Ӧexcel�ļ�  
	          
	        int sheetSize = wb.getNumberOfSheets();  
	        for (int i = 0; i < sheetSize; i++) {//����sheetҳ  
	            Sheet sheet = wb.getSheetAt(i);  
	            List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//��Ӧsheetҳ  
	              
	            List<String> titles = new ArrayList<String>();//�������еı���  
	              
	            int rowSize = sheet.getLastRowNum() + 1;  
	            for (int j = 0; j < rowSize; j++) {//������  
	                Row row = sheet.getRow(j);  
	                if (row == null) {//�Թ�����  
	                    continue;  
	                }  
	                int cellSize = row.getLastCellNum();//�����ж��ٸ���Ԫ��Ҳ�����ж�����  
	                if (j == 0) {//��һ���Ǳ�����  
	                    for (int k = 0; k < cellSize; k++) {  
	                        Cell cell = row.getCell(k);  
	                        titles.add(cell.toString());  
	                    }  
	                } else {//��������������  
	                    Map<String, String> rowMap = new HashMap<String, String>();//��Ӧһ��������  
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
	      case HSSFCell.CELL_TYPE_NUMERIC: // ����
	          //���Ϊʱ���ʽ������
	          if (HSSFDateUtil.isCellDateFormatted(cell)) {      
	             //ע��format��ʽ yyyy-MM-dd hh:mm:ss ��СʱΪ12Сʱ�ƣ���Ҫ24Сʱ�ƣ����Сh��ΪH���ɣ�yyyy-MM-dd HH:mm:ss
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	             value=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();                                 
	               break;
	           } else {
	               value = new DecimalFormat("0").format(cell.getNumericCellValue());
	           }
	          break;
	      case HSSFCell.CELL_TYPE_STRING: // �ַ���
	          value = cell.getStringCellValue();
	          break;
	      case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
	          value = cell.getBooleanCellValue() + "";
	          break;
	      case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
	          value = cell.getCellFormula() + "";
	          break;
	      case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
	          value = "";
	          break;
	      case HSSFCell.CELL_TYPE_ERROR: // ����
	          value = "�Ƿ��ַ�";
	          break;
	      default:
	          value = "δ֪����";
	          break;
		}
		return value;
	}

  
}
