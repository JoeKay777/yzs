package cn.com.seo.control;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import cn.com.seo.base.utils.DateUtil;
import cn.com.seo.base.utils.EscapeUtil;
import cn.com.seo.base.utils.HttpRequestUtil;
import cn.com.seo.base.utils.KeyPrice;
import cn.com.seo.base.utils.PmUtil;
import cn.com.seo.base.utils.PropertiesUtil;
import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.TranDetail;
import cn.com.seo.bean.TranSerial;
import cn.com.seo.bean.User;
import cn.com.seo.bean.UserKeyDabiao;
import cn.com.seo.bean.ZsModel;
import cn.com.seo.service.KeywordsService;
import cn.com.seo.service.LoginService;
import cn.com.seo.service.MoneyService;

@Controller
@Component
public class TimedTaskAction {
	@Autowired
	private KeywordsService kService;
	@Autowired
	private LoginService lService;
	@Autowired
	private MoneyService mService;
	
	public Logger log=Logger.getLogger(TimedTaskAction.class);
	
	@Scheduled(cron = "0 0 1 * * ?")//ÿ���賿����
	public void getPm(){
		List<User> userList=lService.findUserByState("1");//����ʹ�õ��û�
		Keywords words=null;
		if(userList.size()!=0){
			for (User user : userList) {
				if(!"0".equals(user.getRechange_status())){
					List<Keywords> list=kService.KeywordsShowByParam(user.getUsername(), null, null, "2",null,null,null,null,null,null);
					if(list.size()!=0){
						for (Keywords keywords : list) {
							keywords.setZrpai(keywords.getXinpai());
							int sydatenum=keywords.getConsume_datenum()+1;
							keywords.setConsume_datenum(sydatenum);
							if("1".equals(keywords.getPmbh())){
								int dbdatenum=keywords.getDabiao_datenum()+1;
								keywords.setDabiao_datenum(dbdatenum);
							}
							words=PmUtil.getWords(keywords);
							if(words!=null){
								kService.pmUpdate(words);//��ȡ����������
							}
						}
					}
				}else{
					log.info(user.getUsername()+"δ��ֵ�������޷�����");
				}
				
			}
		}
	}
	
	
	
	public TranDetail addTranDetail(Keywords keywords,String dabiao,BigDecimal kk,String date){
		TranDetail detail=new TranDetail();
		detail.setKeywords_id(keywords.getKeywords_id());
		detail.setTran_money(kk);
		detail.setTran_time(date);
	//	detail.setChupai(keywords.getChupai());
		detail.setXinpai(keywords.getXinpai());
		detail.setDomain(keywords.getDomain_address());
		detail.setKeywords(keywords.getKeywords());
		detail.setUsername(keywords.getUsername());
		detail.setSearch_engines(keywords.getSearch_engines());
		detail.setDabiao(dabiao);
		return detail;
	}
	
	
	@Scheduled(cron = "0 0 0 * * ?")//ÿ���賿����
	public void kk(){	
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		Keywords words=null;
		List<User> userList=lService.findUserByState("1");//����ʹ�õ��û�
		if(userList.size()!=0){
			for (User user : userList) {
				if(!"0".equals(user.getRechange_status())){//��ֵ
					List<Keywords> list=kService.KeywordsShowByParam(user.getUsername(), null, null, "2",null,null,null,null,null,null);//���û������õĹؼ���
					BigDecimal kkAmount=BigDecimal.valueOf(0.00);//�ۿ��ܶ�
					List<TranDetail> detailList=new ArrayList<TranDetail>();
					if(list.size()!=0){
						for (Keywords keywords : list) {
							BigDecimal beforeBalance=mService.getBalance(user.getUsername());
				//			words=PmUtil.getWords(keywords);//��ȡ����
				//			int upt=kService.pmUpdate(words);
					//		if(upt!=0){}
							words=keywords;
							BigDecimal kk=this.getkk(words);
							kkAmount=kkAmount.add(kk);
							if(kk.equals(BigDecimal.valueOf(0.00))){//�ؼ�������δ���
								detailList.add(this.addTranDetail(words, "2",kk,yesterday));
								continue;
							}else{//�ؼ����������
								int a=kk.compareTo(beforeBalance);
								if(a==1){
									//����
									int bj=beforeBalance.compareTo(BigDecimal.valueOf(-200));
									if(bj==1||bj==0){
										BigDecimal afterBalance=beforeBalance.subtract(kk);
										mService.updateBalance(afterBalance,"2", user.getUsername());//����ı�ʾ2
									//	afterBalance.toString().replaceAll("-", "");
									/*	String msg="�������㣬Ƿ�ѳ���200Ԫ������ʱֹͣ���Ĺؼ���ʹ��";
										user.setUser_msg(msg);
										lService.update(user);*/
										detailList.add(this.addTranDetail(words,"1",kk,yesterday));
										continue;
									}else{
										String msg="����Ƿ�ѳ���200Ԫ������ʱֹͣ�����йؼ��ֵ�ʹ��";
										user.setUser_msg(msg);
										lService.update(user);
										lService.uptState("0", user.getUsername());
										List<Keywords> keylist=kService.KeywordsShowByParam(user.getUsername(), null, null, "2",null,null,null,null,null,null);//������û��������õĹؼ���
										if(keylist!=null){
											for (Keywords keys : keylist) {
												kService.update(keys.getKeywords_id(), "0");//����Ա����ͣ��ʱ״̬
											}
										}
										log.info(user.getUsername()+"���"+beforeBalance+"Ԫ����Ƿ�ѳ���200Ԫ������ʱֹͣ���û����йؼ��ֵ�ʹ��");
										break;
									}
								}
								if(a==-1||a==0){
									//�۷ѽ��С�ڻ��ߵ������۷�
									BigDecimal afterBalance=beforeBalance.subtract(kk);
									mService.updateBalance(afterBalance,"1", user.getUsername());//������ı�ʾ1
									detailList.add(this.addTranDetail(words,"1",kk,yesterday));
									continue;
								}
							}
						
						}
						mService.saveTranDetail(detailList);
						TranSerial tran=new TranSerial();
						tran.setAccount(kkAmount);
						tran.setDate(yesterday);
						tran.setUsername(user.getUsername());
						tran.setBalance(mService.getBalance(user.getUsername()));
						int save=mService.saveTranSerial(tran);
						if(save!=0){
							log.info(user.getUsername()+date+"�۷ѳɹ���---"+kkAmount+"�����");
						}
						int dabiaoCount=mService.tranList(0,yesterday, user.getUsername(), "1").size();
						UserKeyDabiao ukdb=new UserKeyDabiao(user.getUsername(), String.valueOf(dabiaoCount), yesterday);
						kService.userKeyDbSave(ukdb);
					}else{
						log.info(user.getUsername()+"�޹ؼ��֣��۷Ѳ����޷�ִ��");
					}
				}else{
					log.info(user.getUsername()+"δ��ֵ�������޷����£��۷Ѳ����޷�ִ��");
				}
			}
		}
		
	}
	
	public BigDecimal getkk(Keywords words){
		String xinpai=words.getXinpai();
		BigDecimal price=words.getPrice();
		String kkstyle=words.getKkstyle();
		if("1".equals(kkstyle)){//��ǰ10
			if(xinpai.length()==1||"10".equals(xinpai)){
				int x=Integer.parseInt(xinpai);
				if(x<=10){
					//�۷�
					return price;
				}
			}
		}
		if("2".equals(kkstyle)){//��ǰ3
				if(xinpai.length()==1){
					int x=Integer.parseInt(xinpai);
					if(x<=3){
						//�۷�
						return price;
					}
				}
		}
		return BigDecimal.valueOf(0.00);
	}
	
	/*@Scheduled(cron = " 0 0 1 * * ?")//ÿ���賿
	public void uptZs(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		String date = df.format(new Date());
		List<User> userList=lService.findUserByState("1");//����ʹ�õ��û�
		if(userList.size()!=0){
			for (User user : userList) {
				if(!"0".equals(user.getRechange_status())){
					List<Keywords> list=kService.KeywordsShowByParam(user.getUsername(), null, null, "2",null,null);
					if(list.size()!=0){
						for (Keywords keywords : list) {
							int sydatenum=keywords.getConsume_datenum()+1;
							keywords.setConsume_datenum(sydatenum);
							if("1".equals(keywords.getPmbh())){
								int dbdatenum=keywords.getDabiao_datenum()+1;
								keywords.setDabiao_datenum(dbdatenum);
							}
							String apiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(keywords.getKeywords());;// ��api�ӿ�url
							String result=HttpRequestUtil.sendGet(apiUrl);
							JSONObject jsStr = JSONObject.parseObject(result); //��json�ַ���ת��Ϊjson����
							String data=jsStr.getString("data");//��ȡjson���������ֵ
							int status=jsStr.getInteger("state");//״̬Ϊ1ʱ ��ȡ�ɹ� �����api�ļ�
							String msg=jsStr.getString("msg");
							if(status==1){
								data=data.replace("[", "");
								data=data.replace("]", "");
								JSONObject zsdata = JSONObject.parseObject(data);
								ZsModel zs=JSONObject.toJavaObject(zsdata, ZsModel.class);
								keywords.setPrice(KeyPrice.getPrice( zs.getAllindex(), zs.getMobileindex(), zs.getSo360index(), keywords.getKeywords(), keywords.getSearch_engines()));
								keywords.setMobileIndex(String.valueOf(zs.getMobileindex()));
								keywords.setAllIndex(String.valueOf(zs.getAllindex()));
								keywords.setSo360Index(String.valueOf(zs.getSo360index()));
								keywords.setZsupt_date(date);
								kService.zsUpdate(keywords);
							}
						}
					}
				}else{
					log.info(user.getUsername()+"δ��ֵ��ָ���޷�����");
				}
				
			}
		}
	}
	*/
	
	@Scheduled(cron = " 0 0 2 * * ?")
	public void keywordsStop(){
		List<User> userList=lService.findUserByState("1");
		if(userList.size()!=0){
			for (User user : userList) {
				String username=user.getUsername();
				List<Keywords> keyList=kService.KeywordsShowByParam(username, null, null, "2", null,null,null,null,null,null);
				if(keyList.size()!=0){
					for (Keywords keywords : keyList) {
						int synum=keywords.getConsume_datenum();
						int dbnum=keywords.getDabiao_datenum();
						int keywords_id=keywords.getKeywords_id();
						if(synum==30&&dbnum==0){
							kService.update(keywords_id, "3");//ͣ��
							log.info(username+"�Ĺؼ���'"+keywords.getKeywords()+"'30��δ��꣬ϵͳ�Զ�ͣ��");
						}
						//int keywords_id=keywords.getKeywords_id();
						//List<TranDetail> detailList=mService.tranList(keywords_id, null, username, null);
						/*if(detailList.size()==15){//�ؼ�����15��ʱ�ж�
							int flag=0;
							for (TranDetail tranDetail : detailList) {
								if("2".equals(tranDetail.getDabiao())){//����15��δ���
									flag++;
								}
							}
							if(flag==15){
								kService.update(keywords_id, "3");//ͣ��
								log.info(username+"�Ĺؼ���'"+keywords.getKeywords()+"'15��δ��꣬ϵͳ�Զ�ͣ��");
							}
						}*/
					}
				}
				
			}
		}
	}
	
	
	
	
}
