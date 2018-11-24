package cn.com.seo.bean;

import java.math.BigDecimal;

public class TranSerial {
	/*
	 * 当天扣费流水
	 */
	private int transerial_id;
	private String username;
	private BigDecimal account;
	private String date;
	private BigDecimal balance;//余额
	public int getTranserial_id() {
		return transerial_id;
	}
	public void setTranserial_id(int transerial_id) {
		this.transerial_id = transerial_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	
}
