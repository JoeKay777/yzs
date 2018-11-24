package cn.com.seo.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.Rechange;
import cn.com.seo.bean.TranDetail;
import cn.com.seo.bean.TranSerial;

public interface MoneyService {
	
	public int saveCheck(Rechange re);//充值录入
	public int updateCheck(Rechange re);
	public List<Rechange> rechangeList(String username,String add_time,String payment_style);//查询充值记录
	public List<TranDetail> tranList(int keywords_id,String date,String username,String dabiao);//每个关键字交易记录
	public int saveTranDetail(List<TranDetail> list);//每个关键字每天消费交易
	public BigDecimal tranCount(int keywords_id);//每个关键字交易总额
	public List<TranSerial> allTranSerial(String username,String day,String month,String year);//用户交易记录
	public int saveTranSerial(TranSerial tran);
	
	public BigDecimal introdayDeductAccount(String username,String state,String pmbh);//当天扣除的金额
	
	public BigDecimal getBalance(String username);//获取余额
	public BigDecimal getAmount(String username,String add_time,String payment_style);//获取充值金额

	public int updateBalance(BigDecimal balance,String rechange_status,String username);
	
	public BigDecimal getKeyTranSum(int keywords_id);//获取每一关键字扣费总额
	
	public BigDecimal getAccount(String day,String month,String year,String username);
	public List<TranDetail> tranKeys(int beginDate,int endDate,String username,int keywords_id,String keywords,String dabiao);
	
	public int delTranDetail(String username);
	public int delTranSerial(String username);
	public int delRechange(String username);
	
	public BigDecimal getKeysAccount(int beginDate,int endDate,String username,int keywords_id,String keywords,String dabiao);
}
