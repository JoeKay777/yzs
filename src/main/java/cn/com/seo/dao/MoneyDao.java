package cn.com.seo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.Rechange;
import cn.com.seo.bean.TranDetail;
import cn.com.seo.bean.TranSerial;

public interface MoneyDao {
	public int saveCheck(Rechange re);//充值录入
	public int updateCheck(Rechange re);
	public List<Rechange> rechangeList(@Param("username") String username,
			@Param("add_time") String add_time,@Param("payment_style") String payment_style);//查询充值记录
	public List<TranDetail> tranList(@Param("keywords_id")int keywords_id,@Param("date")String date,@Param("username")String username,@Param("dabiao") String dabiao);//每个关键字交易记录
	public int saveTranDetail(List<TranDetail> detailList);//每个关键字每天消费交易
	public BigDecimal tranCount(@Param("keywords_id") int keywords_id);//每个关键字交易总额
	public List<TranSerial> allTranSerial(@Param("username") String username,@Param("day")String day,
			@Param("month")String month,@Param("year")String year);//用户交易记录
	public int saveTranSerial(TranSerial tran);
	
	public BigDecimal introdayDeductAccount(@Param("username") String username,@Param("state") String state,@Param("pmbh")String pmbh);
	
	public BigDecimal getBalance(@Param("username")String username);//获取余额
	public BigDecimal getAmount(@Param("username") String username,
			@Param("add_time") String add_time,@Param("payment_style") String payment_style);//获取充值金额
	public int updateBalance(@Param("balance")BigDecimal balance,@Param("rechange_status")String rechange_status,@Param("username")String username);
	
	public BigDecimal getKeyTranSum(int keywords_id);//获取每一关键字扣费总额
	
	public BigDecimal getAccount(@Param("day")String day,@Param("month")String month,@Param("year")String year,@Param("username") String username);
	public List<TranDetail> tranKeys(@Param("beginDate") int beginDate,@Param("endDate")int endDate,
			@Param("username") String username,@Param("keywords_id")int keywords_id,
			@Param("keywords")String keywords,@Param("dabiao")String dabiao);
	public BigDecimal getKeysAccount(@Param("beginDate") int beginDate,@Param("endDate")int endDate,
			@Param("username") String username,@Param("keywords_id")int keywords_id,
			@Param("keywords")String keywords,@Param("dabiao")String dabiao);
	public int delTranDetail(@Param("username")String username);
	public int delTranSerial(@Param("username")String username);
	public int delRechange(@Param("username")String username);
}
