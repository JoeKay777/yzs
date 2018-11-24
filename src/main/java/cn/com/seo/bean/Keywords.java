package cn.com.seo.bean;

import java.math.BigDecimal;
import java.util.List;

public class Keywords {
	private int keywords_id;		//id	
	private String search_engines;		//搜索引擎类型 1 百度 3 360 2 mobile
	private String domain_address;	//域名
	private BigDecimal price;		//单价/天
	private int consume_datenum;	//消费天数
	private int dabiao_datenum;		//达标天数
	private String add_date;		//加入时间
	private String chupai;				//初排排名 
	private String zrpai;				//昨日排名
	private String xinpai;				//新排排名
	private String pmbh;				//1达标 2不达标
	private String keywords;			//关键词
	private String last_date;		//最后使用日期
	private String zsupt_date;		//指数更新日期
	private String pmupt_date;		//排名更新日期
	private String allIndex;		//百度整体指数
	private String mobileIndex;	//百度移动指数
	private String so360Index;		//360指数
	private String state;				//状态 1 待审核 2 使用中 3已停用 0管理员停用 5删除回收到回收站
	private String username;		//用户
	private BigDecimal tran_money;//扣费
	private String page_url;//排名所在的链接
	private String remarks;//备注
	private String kkstyle;//扣款类型 1进前10 2进前3
	private String pmstate;//调用接口情况
	private String csdj;//厂商对接情况 1 已处理 0 未处理
	private String deldate;//删除时间
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
