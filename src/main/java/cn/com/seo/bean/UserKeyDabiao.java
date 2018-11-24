package cn.com.seo.bean;
/*
 * 用户每日达标数记录表
 */
public class UserKeyDabiao {
	private String udb_id;
	private String udb_username;
	private String udb_dbnum;
	private String udb_date;
	public String getUdb_id() {
		return udb_id;
	}
	public void setUdb_id(String udb_id) {
		this.udb_id = udb_id;
	}
	public String getUdb_username() {
		return udb_username;
	}
	public void setUdb_username(String udb_username) {
		this.udb_username = udb_username;
	}
	public String getUdb_dbnum() {
		return udb_dbnum;
	}
	public void setUdb_dbnum(String udb_dbnum) {
		this.udb_dbnum = udb_dbnum;
	}
	public String getUdb_date() {
		return udb_date;
	}
	public void setUdb_date(String udb_date) {
		this.udb_date = udb_date;
	}
	public UserKeyDabiao(String udb_username, String udb_dbnum, String udb_date) {
		super();
		this.udb_username = udb_username;
		this.udb_dbnum = udb_dbnum;
		this.udb_date = udb_date;
	}
	public UserKeyDabiao() {
		super();
	}
	
	
}
