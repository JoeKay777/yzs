package cn.com.seo.bean;
/*
 * 关键词指数
 */
public class KeyIndex {
	private int allIndex;		//百度整体指数
	private int mobileIndex;	//百度移动指数
	private int so360Index;		//360指数
	private String username;	//用户名
	private String keywords;	//关键字
	public int getAllIndex() {
		return allIndex;
	}
	public void setAllIndex(int allIndex) {
		this.allIndex = allIndex;
	}
	public int getMobileIndex() {
		return mobileIndex;
	}
	public void setMobileIndex(int mobileIndex) {
		this.mobileIndex = mobileIndex;
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
	public int getSo360Index() {
		return so360Index;
	}
	public void setSo360Index(int so360Index) {
		this.so360Index = so360Index;
	}
	
	
}
