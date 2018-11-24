package cn.com.seo.bean;

public class ZsModel {
	private String keyword;	/*关键词*/
	private int allindex;/*百度整体指数*/
	private int mobileindex;/*百度移动指数*/
	private int so360index;/*360指数*/
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getAllindex() {
		return allindex;
	}
	public void setAllindex(int allindex) {
		this.allindex = allindex;
	}	
	public int getMobileindex() {
		return mobileindex;
	}
	public void setMobileindex(int mobileindex) {
		this.mobileindex = mobileindex;
	}
	public int getSo360index() {
		return so360index;
	}
	public void setSo360index(int so360index) {
		this.so360index = so360index;
	}
	
}
