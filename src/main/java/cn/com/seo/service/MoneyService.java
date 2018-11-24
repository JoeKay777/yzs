package cn.com.seo.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.Rechange;
import cn.com.seo.bean.TranDetail;
import cn.com.seo.bean.TranSerial;

public interface MoneyService {
	
	public int saveCheck(Rechange re);//��ֵ¼��
	public int updateCheck(Rechange re);
	public List<Rechange> rechangeList(String username,String add_time,String payment_style);//��ѯ��ֵ��¼
	public List<TranDetail> tranList(int keywords_id,String date,String username,String dabiao);//ÿ���ؼ��ֽ��׼�¼
	public int saveTranDetail(List<TranDetail> list);//ÿ���ؼ���ÿ�����ѽ���
	public BigDecimal tranCount(int keywords_id);//ÿ���ؼ��ֽ����ܶ�
	public List<TranSerial> allTranSerial(String username,String day,String month,String year);//�û����׼�¼
	public int saveTranSerial(TranSerial tran);
	
	public BigDecimal introdayDeductAccount(String username,String state,String pmbh);//����۳��Ľ��
	
	public BigDecimal getBalance(String username);//��ȡ���
	public BigDecimal getAmount(String username,String add_time,String payment_style);//��ȡ��ֵ���

	public int updateBalance(BigDecimal balance,String rechange_status,String username);
	
	public BigDecimal getKeyTranSum(int keywords_id);//��ȡÿһ�ؼ��ֿ۷��ܶ�
	
	public BigDecimal getAccount(String day,String month,String year,String username);
	public List<TranDetail> tranKeys(int beginDate,int endDate,String username,int keywords_id,String keywords,String dabiao);
	
	public int delTranDetail(String username);
	public int delTranSerial(String username);
	public int delRechange(String username);
	
	public BigDecimal getKeysAccount(int beginDate,int endDate,String username,int keywords_id,String keywords,String dabiao);
}
