package cn.com.seo.bean;

import java.math.BigDecimal;
/*
 * 交易详情表
 */
public class TranDetail {
	private int tran_id;		//主键
	private int keywords_id;
	private BigDecimal tran_money;	//交易金额
	private String tran_time;		//交易时间
	private String username;
	private String keywords;
	private String domain;
	private String chupai;
	private String xinpai;
	private String search_engines;
	private String dabiao;//1 达标 2未达标
	public int getTran_id() {
		return tran_id;
	}
	public void setTran_id(int tran_id) {
		this.tran_id = tran_id;
	}
	public BigDecimal getTran_money() {
		return tran_money;
	}
	public void setTran_money(BigDecimal tran_money) {
		this.tran_money = tran_money;
	}
	public String getTran_time() {
		return tran_time;
	}
	public void setTran_time(String tran_time) {
		this.tran_time = tran_time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getChupai() {
		return chupai;
	}
	public void setChupai(String chupai) {
		this.chupai = chupai;
	}
	public String getXinpai() {
		return xinpai;
	}
	public void setXinpai(String xinpai) {
		this.xinpai = xinpai;
	}
	public String getSearch_engines() {
		return search_engines;
	}
	public void setSearch_engines(String search_engines) {
		this.search_engines = search_engines;
	}
	public String getDabiao() {
		return dabiao;
	}
	public void setDabiao(String dabiao) {
		this.dabiao = dabiao;
	}
	public int getKeywords_id() {
		return keywords_id;
	}
	public void setKeywords_id(int keywords_id) {
		this.keywords_id = keywords_id;
	}
	
	
}
