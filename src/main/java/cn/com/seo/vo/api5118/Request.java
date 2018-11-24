package cn.com.seo.vo.api5118;

public class Request {
	private String url;
	private String keywords;
	private int checkrow;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getCheckrow() {
		return checkrow;
	}
	public void setCheckrow(int checkrow) {
		this.checkrow = checkrow;
	}
	public Request(String url, String keywords, int checkrow) {
		super();
		this.url = url;
		this.keywords = keywords;
		this.checkrow = checkrow;
	}
	
	
}
