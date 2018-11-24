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
		 //1.����������
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            //1.1�����ϲ���Ԫ�����
	        //    CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,4);//��ʼ��,������,��ʼ��,������
	            //1.2ͷ������ʽ
	         //   XSSFCellStyle headStyle = createCellStyle(workbook,(short)16);
	            //1.3�б�����ʽ
	            XSSFCellStyle colStyle = createCellStyle(workbook,(short)13);
	            //2.����������
	            XSSFSheet sheet = workbook.createSheet(DateUtil.getToday()+"�ؼ����б�");
	            //2.1���غϲ���Ԫ�����
	         //   sheet.addMergedRegion(callRangeAddress);
	            //����Ĭ���п�
	            sheet.setDefaultColumnWidth(25);
	           /* //3.������
	            //3.1����ͷ������;��������ͷ����
	            XSSFRow row = sheet.createRow(0);
	            XSSFCell cell = row.createCell(0);
	        
	            //���ص�Ԫ����ʽ
	            cell.setCellStyle(headStyle);
	            cell.setCellValue(DateUtil.getToday()+"�ؼ����б�");
	            */
	            //3.2�����б���;���������б���
	            XSSFRow row2 = sheet.createRow(0);
	            String[] titles = {"�ؼ���","����","��ǰ����","��������","ʹ��״̬"};
	            for(int i=0;i<titles.length;i++)
	            {
	                XSSFCell cell2 = row2.createCell(i);
	                //���ص�Ԫ����ʽ
	                cell2.setCellStyle(colStyle);
	                cell2.setCellValue(titles[i]);
	            }
	            
	            //4.������Ԫ��;���û��б�д��excel
	            if(keyList != null)
	            {
	                for(int j=0;j<keyList.size();j++)
	                {
	                    //����������,ǰ��������,ͷ�����к��б�����
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
	                    	   cell4.setCellValue("�ٶ�PC");
	                    if("2".equals(keyList.get(j).getSearch_engines()))
	                    	   cell4.setCellValue("�ٶ�Mobile");
	                    if("3".equals(keyList.get(j).getSearch_engines()))
	                    	   cell4.setCellValue("360PC");
	                    XSSFCell cell5 = row3.createCell(4);
	                    if("2".equals(keyList.get(j).getState())){
	                    	cell5.setCellValue("ʹ��");
	                    }
	                    if("3".equals(keyList.get(j).getState())){
	                    	cell5.setCellValue("ͣ��");
	                    }
	                }
	            }
	            //5.���
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
	     * @return ��Ԫ����ʽ
	     */
	    private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontsize) {
	        // TODO Auto-generated method stub
	        XSSFCellStyle style = workbook.createCellStyle();
	        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//ˮƽ����
	        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//��ֱ����
	        //��������
	        XSSFFont font = workbook.createFont();
	        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	        font.setFontHeightInPoints(fontsize);
	        //��������
	        style.setFont(font);
	        return style;
	    }
	    
	    public static void expertCheckKeys(List<Keywords> keyList,FileOutputStream out,String date)
	    {
		 try{     
		 //1.����������
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            //1.1�����ϲ���Ԫ�����
	     //       CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,3);//��ʼ��,������,��ʼ��,������
	            //1.2ͷ������ʽ
	     //       XSSFCellStyle headStyle = createCellStyle(workbook,(short)16);
	            //1.3�б�����ʽ
	            XSSFCellStyle colStyle = createCellStyle(workbook,(short)13);
	            //2.����������
	            XSSFSheet sheet = workbook.createSheet(date+"�����ؼ���");
	            //2.1���غϲ���Ԫ�����
	     //       sheet.addMergedRegion(callRangeAddress);
	            //����Ĭ���п�
	            sheet.setDefaultColumnWidth(25);
	     /*       //3.������
	            //3.1����ͷ������;��������ͷ����
	            XSSFRow row = sheet.createRow(0);
	            XSSFCell cell = row.createCell(0);
	        
	            //���ص�Ԫ����ʽ
	            cell.setCellStyle(headStyle);
	            cell.setCellValue(date+"�����ؼ���");*/
	            
	            //3.2�����б���;���������б���
	            XSSFRow row2 = sheet.createRow(0);
	            String[] titles = {"���","����","�ؼ���","����"};
	            for(int i=0;i<titles.length;i++)
	            {
	                XSSFCell cell2 = row2.createCell(i);
	                //���ص�Ԫ����ʽ
	                cell2.setCellStyle(colStyle);
	                cell2.setCellValue(titles[i]);
	            }
	            
	            //4.������Ԫ��;���û��б�д��excel
	            if(keyList != null)
	            {
	                for(int j=0;j<keyList.size();j++)
	                {
	                    //����������,ǰ��������,ͷ�����к��б�����
	                    XSSFRow row3 = sheet.createRow(j+1);
	                    XSSFCell cell1 = row3.createCell(0);
	                    cell1.setCellValue(String.valueOf(keyList.get(j).getKeywords_id()));
	                    XSSFCell cell2 = row3.createCell(1);
	                    if("1".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("�ٶ�PC");
	                    if("2".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("�ٶ�Mobile");
	                    if("3".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("360PC");
	                    XSSFCell cell3 = row3.createCell(2);
	                    cell3.setCellValue(keyList.get(j).getKeywords());
	                    XSSFCell cell4 = row3.createCell(3);
	                    cell4.setCellValue(keyList.get(j).getDomain_address());
	                }
	            }
	            //5.���
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
		 //1.����������
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            //1.1�����ϲ���Ԫ�����
	            CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,3);//��ʼ��,������,��ʼ��,������
	            //1.2ͷ������ʽ
	            XSSFCellStyle headStyle = createCellStyle(workbook,(short)16);
	            //1.3�б�����ʽ
	            XSSFCellStyle colStyle = createCellStyle(workbook,(short)13);
	            //2.����������
	            XSSFSheet sheet = workbook.createSheet("�ؼ���");
	            //2.1���غϲ���Ԫ�����
	            sheet.addMergedRegion(callRangeAddress);
	            //����Ĭ���п�
	            sheet.setDefaultColumnWidth(25);
	            //3.������
	            //3.1����ͷ������;��������ͷ����
	            XSSFRow row = sheet.createRow(0);
	            XSSFCell cell = row.createCell(0);
	        
	            //���ص�Ԫ����ʽ
	            cell.setCellStyle(headStyle);
	            cell.setCellValue(date+"�����ؼ���");
	            
	            //3.2�����б���;���������б���
	            XSSFRow row2 = sheet.createRow(1);
	            String[] titles = {"���","����","�ؼ���","����"};
	            for(int i=0;i<titles.length;i++)
	            {
	                XSSFCell cell2 = row2.createCell(i);
	                //���ص�Ԫ����ʽ
	                cell2.setCellStyle(colStyle);
	                cell2.setCellValue(titles[i]);
	            }
	            
	            //4.������Ԫ��;���û��б�д��excel
	            if(keyList != null)
	            {
	                for(int j=0;j<keyList.size();j++)
	                {
	                    //����������,ǰ��������,ͷ�����к��б�����
	                    XSSFRow row3 = sheet.createRow(j+2);
	                    XSSFCell cell1 = row3.createCell(0);
	                    cell1.setCellValue(String.valueOf(keyList.get(j).getKeywords_id()));
	                    XSSFCell cell2 = row3.createCell(1);
	                    if("1".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("�ٶ�PC");
	                    if("2".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("�ٶ�Mobile");
	                    if("3".equals(keyList.get(j).getSearch_engines()))
	                    	   cell2.setCellValue("360PC");
	                    XSSFCell cell3 = row3.createCell(2);
	                    cell3.setCellValue(keyList.get(j).getKeywords());
	                    XSSFCell cell4 = row3.createCell(3);
	                    cell4.setCellValue(keyList.get(j).getDomain_address());
	                }
	            }
	            //5.���
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
