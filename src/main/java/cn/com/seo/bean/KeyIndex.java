package cn.com.seo.bean;
/*
 * �ؼ���ָ��
 */
public class KeyIndex {
	private int allIndex;		//�ٶ�����ָ��
	private int mobileIndex;	//�ٶ��ƶ�ָ��
	private int so360Index;		//360ָ��
	private String username;	//�û���
	private String keywords;	//�ؼ���
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
