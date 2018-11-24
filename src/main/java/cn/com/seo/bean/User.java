package cn.com.seo.bean;

import java.math.BigDecimal;

public class User {
	private int user_id;		//用户id 序号 
	private String username;	//用户名
	private String password;	//密码
	private String oldpwd;//原来密码
	private String mail;		//邮箱
	private String phone;		//联系方式
	private String role_type;		//权限分组  0 admin 1 
	private String user_status;	//用户状态  0停用 1正常
	private String company;		//联系人 
	private String register_time;//注册时间
	private String qq;
	private BigDecimal balance;//余额
	private String ulevel;//用户级别
	private String keyamount;//关键字上限
	private String rechange_status;//用户充值状态 0 未充值 1 余额充足 2 余额不足 
	private String user_msg;//提示消息


	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	
	public String getKeyamount() {
		return keyamount;
	}
	public void setKeyamount(String keyamount) {
		this.keyamount = keyamount;
	}
	public String getUlevel() {
		return ulevel;
	}
	public void setUlevel(String ulevel) {
		this.ulevel = ulevel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getRechange_status() {
		return rechange_status;
	}
	public void setRechange_status(String rechange_status) {
		this.rechange_status = rechange_status;
	}
	public String getUser_msg() {
		return user_msg;
	}
	public void setUser_msg(String user_msg) {
		this.user_msg = user_msg;
	}
	
	
}
