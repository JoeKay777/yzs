package cn.com.seo.base.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;

import org.apache.commons.lang.StringEscapeUtils;

public class ByPage{
	public static void main(String[] args) throws UnsupportedEncodingException {
			/*String a = StringEscapeUtils.escapeCsv("轻工业");
			String b = StringEscapeUtils.escapeHtml("轻工业");
			String c = StringEscapeUtils.escapeJava("轻工业");
			String d= StringEscapeUtils.escapeJavaScript("轻工业");
			String e= StringEscapeUtils.escapeSql("轻工业");
			String f= StringEscapeUtils.escapeXml("轻工业");
	        System.out.println(a);  
	        System.out.println(b); 
	        System.out.println(c); 
	        System.out.println(d); 
	        System.out.println(e); 
	        System.out.println(f); 
	        
	        String aac="&#195;";
	        String aa = StringEscapeUtils.unescapeHtml(aac);  
	        System.out.println(aa); 
	        System.out.println(BigDecimal.valueOf(0));*/
/*		Calendar   cal   =   Calendar.getInstance();
		  cal.add(Calendar.DATE,   -1);
		  String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		  System.out.println(yesterday);*/
	/*	String keywords="十三水赌场";
		String keywordsModel=PropertiesUtil.getProperty("keywords_bocai");
		String[] str=keywordsModel.split("，");
		for (String string : str) {
			boolean b=keywords.contains(string);
			if(b){
				System.out.println(string+b);
				break;
			}
		}*/
		/*
		String[] str=new String[10];
		for(int i=0;i<10;i++){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -(i+1));
			String d = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
			str[i]=d;
		}
		System.out.println(Arrays.toString(str));*/
		String str="http://www.baidu.com";
		String s=str.replace("http://", "").trim();
		System.out.println(s);
	}
}