package cn.com.seo.bean;

import java.math.BigDecimal;
/*
 * ��ֵ��¼��
 */
public class Rechange {
	private int rechange_id;	//id
	private String username;	//�û�
	private BigDecimal amount;	//��ֵ���
	private String add_time;	//��ֵʱ��
//	private BigDecimal balance;	//���
	private String payment_style;	//֧����ʽ 1΢�� 2֧���� 3����
//	private String payment_result;	//֧����� 0ʧ�� 1�ɹ�
	private String re_describe;		//������Ϣ �����տ�����Ϣ
	public int getRechange_id() {
		return rechange_id;
	}
	public void setRechange_id(int rechange_id) {
		this.rechange_id = rechange_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
/*	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
*/

	public String getPayment_style() {
		return payment_style;
	}
	
	public void setPayment_style(String payment_style) {
		this.payment_style = payment_style;
	}
/*	public String getPayment_result() {
		return payment_result;
	}
	public void setPayment_result(String payment_result) {
		this.payment_result = payment_result;
	}
	*/
	public String getRe_describe() {
		return re_describe;
	}
	public void setRe_describe(String re_describe) {
		this.re_describe = re_describe;
	}
	
}
