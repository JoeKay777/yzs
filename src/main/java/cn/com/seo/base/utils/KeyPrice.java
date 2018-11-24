package cn.com.seo.base.utils;

import java.math.BigDecimal;

public class KeyPrice {
	public static BigDecimal getNormalPrice(int index){
		double price=99.99;
		if(0<=index&&index<=500){
			price=7;
		}else if(501<=index&&index<=1000){
			price=10;
		}else if(1001<=index&&index<=1500){
			price=13;
		}else if(1501<=index&&index<=2000){
			price=16;
		}else if(2001<=index&&index<=2500){
			price=19;
		}else if(2501<=index&&index<=3000){
			price=22;
		}
		return BigDecimal.valueOf(price);
	}
	
	public static BigDecimal getBlackPrice(int index){
		double price = 99.99 ;
		if(0<=index&&index<=100){
			price=28;
		}else if(101<=index&&index<=200){
			price=42;
		}else if(201<=index&&index<=500){
			price=56;
		}else if(501<=index&&index<=800){
			price=70;
		}else if(801<=index&&index<=1000){
			price=84;
		}else if(1001<=index&&index<=2000){
			price=98;
		}
		return BigDecimal.valueOf(price);
	}
	public static BigDecimal getPrice(int allIndex,int mobileIndex,int so360Index,String keyword,String webid){
		String keywordsModel=PropertiesUtil.getProperty("keywords_bocai");
		String[] str=keywordsModel.split("£¬");
		boolean b=false;
		for (String string : str) {
			b=keyword.contains(string);
			if(b){
				break;
			}
		}
		BigDecimal price = null;
		if(webid.equals("1")){
			if(b==false){
				price=KeyPrice.getNormalPrice(allIndex);
			}else{
				price=KeyPrice.getBlackPrice(allIndex);
			}
		}
		if(webid.equals("2")){
			if(b==false){
				price=KeyPrice.getNormalPrice(mobileIndex);
			}else{
				price=KeyPrice.getBlackPrice(mobileIndex);
			}
		}
		if(webid.equals("3")){
			if(b==false){
				price=KeyPrice.getNormalPrice(so360Index);
			}else{
				price=KeyPrice.getBlackPrice(so360Index);
			}
		}
		return price;
	}
	
	public static String[] getAllPrice(int allIndex,int mobileIndex,int so360Index,String keyword){
		String keywordsModel=PropertiesUtil.getProperty("keywords_bocai");
		String[] strmodel=keywordsModel.split("£¬");
		boolean b=false;
		for (String string : strmodel) {
			b=keyword.contains(string);
			if(b){
				break;
			}
		}
		BigDecimal baiduPCPrice=null;
		BigDecimal baiduMoPrice=null;
		BigDecimal so360Price=null;
		if(b==false){
			baiduPCPrice=KeyPrice.getNormalPrice(allIndex);
		}else{
			baiduPCPrice=KeyPrice.getBlackPrice(allIndex);
		}
		if(b==false){
			baiduMoPrice=KeyPrice.getNormalPrice(mobileIndex);
		}else{
			baiduMoPrice=KeyPrice.getBlackPrice(mobileIndex);
		}
		if(b==false){
			so360Price=KeyPrice.getNormalPrice(so360Index);
		}else{
			so360Price=KeyPrice.getBlackPrice(so360Index);
		}
		String[] str={baiduPCPrice.toString(),baiduMoPrice.toString(),so360Price.toString()};
		return str;
	}
}
