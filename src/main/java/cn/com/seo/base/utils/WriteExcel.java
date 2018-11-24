package cn.com.seo.base.utils;

import java.io.FileOutputStream;
import java.util.List;



import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.seo.bean.Keywords;


public class WriteExcel {
	 public static void exportKeywordsExcel(List<Keywords> keyList,FileOutputStream out)
	    {
		 try{     
		 //1.创建工作簿
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            //1.1创建合并单元格对象
	        //    CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,4);//起始行,结束行,起始列,结束列
	            //1.2头标题样式
	         //   XSSFCellStyle headStyle = createCellStyle(workbook,(short)16);
	            //1.3列标题样式
	            XSSFCellStyle colStyle = createCellStyle(workbook,(short)13);
	            //2.创建工作表
	            XSSFSheet sheet = workbook.createSheet(DateUtil.getToday()+"关键词列表");
	            //2.1加载合并单元格对象
	         //   sheet.addMergedRegion(callRangeAddress);
	            //设置默认列宽
	            sheet.setDefaultColumnWidth(25);
	           /* //3.创建行
	            //3.1创建头标题行;并且设置头标题
	            XSSFRow row = sheet.createRow(0);
	            XSSFCell cell = row.createCell(0);
	        
	            //加载单元格样式
	            cell.setCellStyle(headStyle);
	            cell.setCellValue(DateUtil.getToday()+"关键词列表");
	            */
	            //3.2创建列标题;并且设置列标题
	            XSSFRow row2 = sheet.createRow(0);
	            String[] titles = {"关键词","域名","当前排名","搜索引擎","使用状态"};
	            for(int i=0;i<titles.length;i++)
	            {
	                XSSFCell cell2 = row2.createCell(i);
	                //加载单元格样式
	                cell2.setCellStyle(colStyle);
	                cell2.setCellValue(titles[i]);
	            }
	            
	            //4.操作单元格;将用户列表写入excel
	            if(keyList != null)
	            {
	                for(int j=0;j<keyList.size();j++)
	                {
	                    //创建数据行,前面有两行,头标题行和列标题行
	                    XSSFRow row3 = sheet.createRow(j+1);
	                    XSSFCell cell1 = row3.createCell(0);
	                    cell1.setCellValue(keyList.get(j).getKeywords());
	                    XSSFCell cell2 = row3.createCell(1);
	                    cell2.setCellValue("http://"+keyList.get(j).getDomain_address());
	                    XSSFCell cell3 = row3.createCell(2);
	                    cell3.setCellValue(keyList.get(j).getXinpai());
	               /*     if("9999".equals(keyList.get(j).getXinpai())){
	                    	cell3.setCellValue(keyList.get(j).getChupai());
	                    }else{
	                    	cell3.setCellValue(keyList.get(j).getXinpai());
	                    }*/
	                    XSSFCell cell4 = row3.createCell(3);
	                    if("1".equals(keyList.get(j).getSearch_engines()))
	                    	   cell4.setCellValue("百度PC");
	                    if("2".equals(keyList.get(j).getSearch_engines()))
	                    	   cell4.setCellValue("百度Mobile");
	                    if("3".equals(keyList.get(j).getSearch_engines()))
	                    	   cell4.setCellValue("360PC");
	                    XSSFCell cell5 = row3.createCell(4);
	                    if("2".equals(keyList.get(j).getState())){
	                    	cell5.setCellValue("使用");
	                    }
	                    if("3".equals(keyList.get(j).getState())){
	                    	cell5.setCellValue("停用");
	                    }
	                }
	            }
	            //5.输出
	            workbook.write(out);
	            workbook.close();
	            if(out!=null)
	            	out.close();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * 
	     * @param workbook
	     * @param fontsize
	     * @return 单元格样式
	     */
	    private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontsize) {
	        // TODO Auto-generated method stub
	        XSSFCellStyle style = workbook.createCellStyle();
	        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
	        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
	        //创建字体
	        XSSFFont font = workbook.createFont();
	        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	        font.setFontHeightInPoints(fontsize);
	        //加载字体
	        style.setFont(font);
	        return style;
	    }
	    
	    public static void expertCheckKeys(List<Keywords> keyList,FileOutputStream out,String date)
	    {
		 try{     
		 //1.创建工作簿
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            //1.1创建合并单元格对象
	     //       CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,3);//起始行,结束行,起始列,结束列
	            //1.2头标题样式
	     //       XSSFCellStyle headStyle = createCellStyle(workbook,(short)16);
	            //1.3列标题样式
	            XSSFCellStyle colStyle = createCellStyle(workbook,(short)13);
	            //2.创建工作表
	            XSSFSheet sheet = workbook.createSheet(date+"新增关键字");
	            //2.1加载合并单元格对象
	     //       sheet.addMergedRegion(callRangeAddress);
	            //设置默认列宽
	            sheet.setDefaultColumnWidth(25);
	     /*       //3.创建行
	            //3.1创建头标题行;并且设置头标题
	            XSSFRow row = sheet.createRow(0);
	            XSSFCell cell = row.createCell(0);
	        
	            //加载单元格样式
	            cell.setCellStyle(headStyle);
	            cell.setCellValue(date+"新增关键字");*/
	            
	            //3.2创建列标题;并且设置列标题
	            XSSFRow row2 = sheet.createRow(0);
	            String[] titles = {"序号","类型","关键字","域名"};
	            for(int i=0;i<titles.length;i++)
	            {
	                XSSFCell cell2 = row2.createCell(i);
	                //加载单元格样式
	                cell2.setCellStyle(colStyle);
	                cell2.setCellValue(titles[i]);
	            }
	            
	            //4.操作单元格;将用户列表写入excel
	            if(keyList != null)
	            {
	                for(int j=0;j<keyList.size();j++)
	                {
	                    //创建数据行,前面有两行,头标题行和列标题行
	                    XSSFRow row3 = sheet.createRow(j+1);
	                    XSSFCell cell1 = row3.createCell(0);
	                    cell1.setCellValue(String.valueOf(keyList.get(j).getKeywords_id()));
	                    XSSFCell cell2 = row3.createCell(1);
	                    if("1".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("百度PC");
	                    if("2".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("百度Mobile");
	                    if("3".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("360PC");
	                    XSSFCell cell3 = row3.createCell(2);
	                    cell3.setCellValue(keyList.get(j).getKeywords());
	                    XSSFCell cell4 = row3.createCell(3);
	                    cell4.setCellValue(keyList.get(j).getDomain_address());
	                }
	            }
	            //5.输出
	            workbook.write(out);
	            workbook.close();
	            if(out!=null)
	            	out.close();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	    
	  /*  public static void expertKeys(List<Keywords> keyList,FileOutputStream out,String date)
	    {
		 try{     
		 //1.创建工作簿
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            //1.1创建合并单元格对象
	            CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,3);//起始行,结束行,起始列,结束列
	            //1.2头标题样式
	            XSSFCellStyle headStyle = createCellStyle(workbook,(short)16);
	            //1.3列标题样式
	            XSSFCellStyle colStyle = createCellStyle(workbook,(short)13);
	            //2.创建工作表
	            XSSFSheet sheet = workbook.createSheet("关键字");
	            //2.1加载合并单元格对象
	            sheet.addMergedRegion(callRangeAddress);
	            //设置默认列宽
	            sheet.setDefaultColumnWidth(25);
	            //3.创建行
	            //3.1创建头标题行;并且设置头标题
	            XSSFRow row = sheet.createRow(0);
	            XSSFCell cell = row.createCell(0);
	        
	            //加载单元格样式
	            cell.setCellStyle(headStyle);
	            cell.setCellValue(date+"新增关键字");
	            
	            //3.2创建列标题;并且设置列标题
	            XSSFRow row2 = sheet.createRow(1);
	            String[] titles = {"序号","类型","关键字","域名"};
	            for(int i=0;i<titles.length;i++)
	            {
	                XSSFCell cell2 = row2.createCell(i);
	                //加载单元格样式
	                cell2.setCellStyle(colStyle);
	                cell2.setCellValue(titles[i]);
	            }
	            
	            //4.操作单元格;将用户列表写入excel
	            if(keyList != null)
	            {
	                for(int j=0;j<keyList.size();j++)
	                {
	                    //创建数据行,前面有两行,头标题行和列标题行
	                    XSSFRow row3 = sheet.createRow(j+2);
	                    XSSFCell cell1 = row3.createCell(0);
	                    cell1.setCellValue(String.valueOf(keyList.get(j).getKeywords_id()));
	                    XSSFCell cell2 = row3.createCell(1);
	                    if("1".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("百度PC");
	                    if("2".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("百度Mobile");
	                    if("3".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("360PC");
	                    XSSFCell cell3 = row3.createCell(2);
	                    cell3.setCellValue(keyList.get(j).getKeywords());
	                    XSSFCell cell4 = row3.createCell(3);
	                    cell4.setCellValue(keyList.get(j).getDomain_address());
	                }
	            }
	            //5.输出
	            workbook.write(out);
	            workbook.close();
	            if(out!=null)
	            	out.close();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    }*/
}
