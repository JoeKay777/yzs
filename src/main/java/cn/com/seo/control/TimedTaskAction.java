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
	
	@Scheduled(cron = "0 0 1 * * ?")//每天凌晨触发
	public void getPm(){
		List<User> userList=lService.findUserByState("1");//正常使用的用户
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
								kService.pmUpdate(words);//获取排名并更新
							}
						}
					}
				}else{
					log.info(user.getUsername()+"未充值，排名无法更新");
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
	
	
	@Scheduled(cron = "0 0 0 * * ?")//每天凌晨触发
	public void kk(){	
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		Keywords words=null;
		List<User> userList=lService.findUserByState("1");//正常使用的用户
		if(userList.size()!=0){
			for (User user : userList) {
				if(!"0".equals(user.getRechange_status())){//充值
					List<Keywords> list=kService.KeywordsShowByParam(user.getUsername(), null, null, "2",null,null,null,null,null,null);//该用户下启用的关键字
					BigDecimal kkAmount=BigDecimal.valueOf(0.00);//扣款总额
					List<TranDetail> detailList=new ArrayList<TranDetail>();
					if(list.size()!=0){
						for (Keywords keywords : list) {
							BigDecimal beforeBalance=mService.getBalance(user.getUsername());
				//			words=PmUtil.getWords(keywords);//获取排名
				//			int upt=kService.pmUpdate(words);
					//		if(upt!=0){}
							words=keywords;
							BigDecimal kk=this.getkk(words);
							kkAmount=kkAmount.add(kk);
							if(kk.equals(BigDecimal.valueOf(0.00))){//关键字排名未达标
								detailList.add(this.addTranDetail(words, "2",kk,yesterday));
								continue;
							}else{//关键字排名达标
								int a=kk.compareTo(beforeBalance);
								if(a==1){
									//余额不足
									int bj=beforeBalance.compareTo(BigDecimal.valueOf(-200));
									if(bj==1||bj==0){
										BigDecimal afterBalance=beforeBalance.subtract(kk);
										mService.updateBalance(afterBalance,"2", user.getUsername());//余额不足的标示2
									//	afterBalance.toString().replaceAll("-", "");
									/*	String msg="您的余额不足，欠费超过200元，将暂时停止您的关键字使用";
										user.setUser_msg(msg);
										lService.update(user);*/
										detailList.add(this.addTranDetail(words,"1",kk,yesterday));
										continue;
									}else{
										String msg="您已欠费超过200元，需暂时停止您所有关键字的使用";
										user.setUser_msg(msg);
										lService.update(user);
										lService.uptState("0", user.getUsername());
										List<Keywords> keylist=kService.KeywordsShowByParam(user.getUsername(), null, null, "2",null,null,null,null,null,null);//查出该用户所有启用的关键字
										if(keylist!=null){
											for (Keywords keys : keylist) {
												kService.update(keys.getKeywords_id(), "0");//管理员批量停用时状态
											}
										}
										log.info(user.getUsername()+"余额"+beforeBalance+"元，已欠费超过200元，已暂时停止该用户所有关键字的使用");
										break;
									}
								}
								if(a==-1||a==0){
									//扣费金额小于或者等于余额，扣费
									BigDecimal afterBalance=beforeBalance.subtract(kk);
									mService.updateBalance(afterBalance,"1", user.getUsername());//余额充足的标示1
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
							log.info(user.getUsername()+date+"扣费成功：---"+kkAmount+"人民币");
						}
						int dabiaoCount=mService.tranList(0,yesterday, user.getUsername(), "1").size();
						UserKeyDabiao ukdb=new UserKeyDabiao(user.getUsername(), String.valueOf(dabiaoCount), yesterday);
						kService.userKeyDbSave(ukdb);
					}else{
						log.info(user.getUsername()+"无关键字，扣费操作无法执行");
					}
				}else{
					log.info(user.getUsername()+"未充值，排名无法更新，扣费操作无法执行");
				}
			}
		}
		
	}
	
	public BigDecimal getkk(Keywords words){
		String xinpai=words.getXinpai();
		BigDecimal price=words.getPrice();
		String kkstyle=words.getKkstyle();
		if("1".equals(kkstyle)){//进前10
			if(xinpai.length()==1||"10".equals(xinpai)){
				int x=Integer.parseInt(xinpai);
				if(x<=10){
					//扣费
					return price;
				}
			}
		}
		if("2".equals(kkstyle)){//进前3
				if(xinpai.length()==1){
					int x=Integer.parseInt(xinpai);
					if(x<=3){
						//扣费
						return price;
					}
				}
		}
		return BigDecimal.valueOf(0.00);
	}
	
	/*@Scheduled(cron = " 0 0 1 * * ?")//每天凌晨
	public void uptZs(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date = df.format(new Date());
		List<User> userList=lService.findUserByState("1");//正常使用的用户
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
							String apiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(keywords.getKeywords());;// 的api接口url
							String result=HttpRequestUtil.sendGet(apiUrl);
							JSONObject jsStr = JSONObject.parseObject(result); //把json字符串转换为json对象
							String data=jsStr.getString("data");//获取json对象的属性值
							int status=jsStr.getInteger("state");//状态为1时 获取成功 详情见api文件
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
					log.info(user.getUsername()+"未充值，指数无法更新");
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
							kService.update(keywords_id, "3");//停用
							log.info(username+"的关键字'"+keywords.getKeywords()+"'30天未达标，系统自动停用");
						}
						//int keywords_id=keywords.getKeywords_id();
						//List<TranDetail> detailList=mService.tranList(keywords_id, null, username, null);
						/*if(detailList.size()==15){//关键字用15天时判断
							int flag=0;
							for (TranDetail tranDetail : detailList) {
								if("2".equals(tranDetail.getDabiao())){//连续15天未达标
									flag++;
								}
							}
							if(flag==15){
								kService.update(keywords_id, "3");//停用
								log.info(username+"的关键字'"+keywords.getKeywords()+"'15天未达标，系统自动停用");
							}
						}*/
					}
				}
				
			}
		}
	}
	
	
	
	
}
