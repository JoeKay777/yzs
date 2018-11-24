package cn.com.seo.bean;

import java.math.BigDecimal;
import java.util.List;

public class Keywords {
	private int keywords_id;		//id	
	private String search_engines;		//������������ 1 �ٶ� 3 360 2 mobile
	private String domain_address;	//����
	private BigDecimal price;		//����/��
	private int consume_datenum;	//��������
	private int dabiao_datenum;		//�������
	private String add_date;		//����ʱ��
	private String chupai;				//�������� 
	private String zrpai;				//��������
	private String xinpai;				//��������
	private String pmbh;				//1��� 2�����
	private String keywords;			//�ؼ���
	private String last_date;		//���ʹ������
	private String zsupt_date;		//ָ����������
	private String pmupt_date;		//������������
	private String allIndex;		//�ٶ�����ָ��
	private String mobileIndex;	//�ٶ��ƶ�ָ��
	private String so360Index;		//360ָ��
	private String state;				//״̬ 1 ����� 2 ʹ���� 3��ͣ�� 0����Աͣ�� 5ɾ�����յ�����վ
	private String username;		//�û�
	private BigDecimal tran_money;//�۷�
	private String page_url;//�������ڵ�����
	private String remarks;//��ע
	private String kkstyle;//�ۿ����� 1��ǰ10 2��ǰ3
	private String pmstate;//���ýӿ����
	private String csdj;//���̶Խ���� 1 �Ѵ��� 0 δ����
	private String deldate;//ɾ��ʱ��
	private List<TranDetail> trans;
	
	public List<TranDetail> getTrans() {
		return trans;
	}
	public void setTrans(List<TranDetail> trans) {
		this.trans = trans;
	}
	public int getKeywords_id() {
		return keywords_id;
	}
	public void setKeywords_id(int keywords_id) {
		this.keywords_id = keywords_id;
	}
	
	public String getSearch_engines() {
		return search_engines;
	}
	public void setSearch_engines(String search_engines) {
		this.search_engines = search_engines;
	}
	public String getDomain_address() {
		return domain_address;
	}
	public void setDomain_address(String domain_address) {
		this.domain_address = domain_address;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getConsume_datenum() {
		return consume_datenum;
	}
	public void setConsume_datenum(int consume_datenum) {
		this.consume_datenum = consume_datenum;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
	
	public String getLast_date() {
		return last_date;
	}
	public void setLast_date(String last_date) {
		this.last_date = last_date;
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
	public String getPmbh() {
		return pmbh;
	}
	public void setPmbh(String pmbh) {
		this.pmbh = pmbh;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public BigDecimal getTran_money() {
		return tran_money;
	}
	public void setTran_money(BigDecimal tran_money) {
		this.tran_money = tran_money;
	}
	public String getPage_url() {
		return page_url;
	}
	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}
	public String getAllIndex() {
		return allIndex;
	}
	public void setAllIndex(String allIndex) {
		this.allIndex = allIndex;
	}
	public String getMobileIndex() {
		return mobileIndex;
	}
	public void setMobileIndex(String mobileIndex) {
		this.mobileIndex = mobileIndex;
	}
	public String getSo360Index() {
		return so360Index;
	}
	public void setSo360Index(String so360Index) {
		this.so360Index = so360Index;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getDabiao_datenum() {
		return dabiao_datenum;
	}
	public void setDabiao_datenum(int dabiao_datenum) {
		this.dabiao_datenum = dabiao_datenum;
	}
	public String getZsupt_date() {
		return zsupt_date;
	}
	public void setZsupt_date(String zsupt_date) {
		this.zsupt_date = zsupt_date;
	}
	public String getPmupt_date() {
		return pmupt_date;
	}
	public void setPmupt_date(String pmupt_date) {
		this.pmupt_date = pmupt_date;
	}
	public String getZrpai() {
		return zrpai;
	}
	public void setZrpai(String zrpai) {
		this.zrpai = zrpai;
	}
	public String getKkstyle() {
		return kkstyle;
	}
	public void setKkstyle(String kkstyle) {
		this.kkstyle = kkstyle;
	}
	public String getPmstate() {
		return pmstate;
	}
	public void setPmstate(String pmstate) {
		this.pmstate = pmstate;
	}
	public String getCsdj() {
		return csdj;
	}
	public void setCsdj(String csdj) {
		this.csdj = csdj;
	}
	public String getDeldate() {
		return deldate;
	}
	public void setDeldate(String deldate) {
		this.deldate = deldate;
	}
	
	
}
