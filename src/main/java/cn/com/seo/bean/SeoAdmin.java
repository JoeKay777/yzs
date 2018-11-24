package cn.com.seo.bean;

public class SeoAdmin {
	private int admin_id;
	private String admin_name;
	private String admin_pass;
	private String admin_beforepass;
	private String admin_truename;
	private String admin_tel;		
	private String admin_mail;
	private String admin_qq;
	private String admin_adddate;
	private String admin_grade; //管理员级别
	
	
	public String getAdmin_qq() {
		return admin_qq;
	}
	public void setAdmin_qq(String admin_qq) {
		this.admin_qq = admin_qq;
	}
	public String getAdmin_truename() {
		return admin_truename;
	}
	public void setAdmin_truename(String admin_truename) {
		this.admin_truename = admin_truename;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;	
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}
	public String getAdmin_tel() {
		return admin_tel;
	}
	public void setAdmin_tel(String admin_tel) {
		this.admin_tel = admin_tel;
	}
	public String getAdmin_mail() {
		return admin_mail;
	}
	public void setAdmin_mail(String admin_mail) {
		this.admin_mail = admin_mail;
	}
	public String getAdmin_adddate() {
		return admin_adddate;
	}
	public void setAdmin_adddate(String admin_adddate) {
		this.admin_adddate = admin_adddate;
	}
	public String getAdmin_beforepass() {
		return admin_beforepass;
	}
	public void setAdmin_beforepass(String admin_beforepass) {
		this.admin_beforepass = admin_beforepass;
	}
	
	public String getAdmin_grade() {
		return admin_grade;
	}
	public void setAdmin_grade(String admin_grade) {
		this.admin_grade = admin_grade;
	}
	public SeoAdmin( String admin_name, String admin_pass, String admin_tel, String admin_mail,
			String admin_adddate) {
		super();
		this.admin_name = admin_name;
		this.admin_pass = admin_pass;
		this.admin_tel = admin_tel;
		this.admin_mail = admin_mail;
		this.admin_adddate = admin_adddate;
	}
	

	
	public SeoAdmin(int admin_id, String admin_truename, String admin_tel, String admin_mail, String admin_qq) {
		super();
		this.admin_id = admin_id;
		this.admin_truename = admin_truename;
		this.admin_tel = admin_tel;
		this.admin_mail = admin_mail;
		this.admin_qq = admin_qq;
	}
	public SeoAdmin(){}
	
}
