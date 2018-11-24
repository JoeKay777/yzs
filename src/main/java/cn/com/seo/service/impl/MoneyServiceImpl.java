package cn.com.seo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.seo.bean.Rechange;
import cn.com.seo.bean.TranDetail;
import cn.com.seo.bean.TranSerial;
import cn.com.seo.dao.MoneyDao;
import cn.com.seo.service.MoneyService;

@Service
public class MoneyServiceImpl implements MoneyService{
	@Autowired
	private MoneyDao dao;
	public int saveCheck(Rechange re) {
		// TODO Auto-generated method stub
		return dao.saveCheck(re);
	}

	public int updateCheck(Rechange re) {
		// TODO Auto-generated method stub
		return dao.updateCheck(re);
	}



	public int saveTranDetail(List<TranDetail> list) {
		// TODO Auto-generated method stub
		return dao.saveTranDetail(list);
	}

	public BigDecimal tranCount(int keywords_id) {
		// TODO Auto-generated method stub
		return dao.tranCount(keywords_id);
	}


	public int saveTranSerial(TranSerial tran) {
		// TODO Auto-generated method stub
		return dao.saveTranSerial(tran);
	}

	public BigDecimal introdayDeductAccount(String username, String state,String pmbh) {
		// TODO Auto-generated method stub
		return dao.introdayDeductAccount(username, state, pmbh);
	}




	public BigDecimal getKeyTranSum(int keywords_id) {
		// TODO Auto-generated method stub
		return dao.getKeyTranSum(keywords_id);
	}

	public List<TranDetail> tranList(int keywords_id, String date, String username,String dabiao) {
		// TODO Auto-generated method stub
		return dao.tranList(keywords_id, date, username,dabiao );
	}


	public List<Rechange> rechangeList(String username, String add_time, String payment_style) {
		// TODO Auto-generated method stub
		return dao.rechangeList(username, add_time, payment_style);
	}

	public BigDecimal getAccount(String day, String month, String year, String username) {
		// TODO Auto-generated method stub
		return dao.getAccount(day, month, year, username);
	}

	public List<TranDetail> tranKeys(int beginDate, int endDate, String username, int keywords_id,
			String keywords, String dabiao) {
		// TODO Auto-generated method stub
		return dao.tranKeys(beginDate, endDate, username, keywords_id, keywords, dabiao);
	}

	public int delTranDetail(String username) {
		// TODO Auto-generated method stub
		return dao.delTranDetail(username);
	}

	public int delTranSerial(String username) {
		// TODO Auto-generated method stub
		return dao.delTranSerial(username);
	}

	public int delRechange(String username) {
		// TODO Auto-generated method stub
		return dao.delRechange(username);
	}

	public int updateBalance(BigDecimal balance, String rechange_status, String username) {
		// TODO Auto-generated method stub
		return dao.updateBalance(balance, rechange_status, username);
	}

	public BigDecimal getBalance(String username) {
		// TODO Auto-generated method stub
		return dao.getBalance(username);
	}

	public List<TranSerial> allTranSerial(String username, String day, String month, String year) {
		// TODO Auto-generated method stub
		return dao.allTranSerial(username, day, month, year);
	}

	public BigDecimal getAmount(String username, String add_time, String payment_style) {
		// TODO Auto-generated method stub
		return dao.getAmount(username, add_time, payment_style);
	}

	public BigDecimal getKeysAccount(int beginDate, int endDate, String username, int keywords_id, String keywords,
			String dabiao) {
		// TODO Auto-generated method stub
		return dao.getKeysAccount(beginDate, endDate, username, keywords_id, keywords, dabiao);
	}



	
}
