package cn.com.seo.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.seo.bean.Keywords;

public class PmUtil {
	private static Logger log=Logger.getLogger(PmUtil.class);
	
	public static Keywords getPm(String apiUrl,String apiKey,String apiParam,Keywords key){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		String date = df.format(new Date());
		String taskResult=HttpRequestUtil.sendPost(apiUrl, apiParam ,apiKey);
		JSONObject taskStr = JSONObject.parseObject(taskResult); //��json�ַ���ת��Ϊjson����
		String taskData=taskStr.getString("data");
		String errcode=taskStr.getString("errcode");
		String errmsg=taskStr.getString("errmsg");
		log.info(key.getKeywords()+"�������ӿ�1��ѯ״̬��"+errcode+" "+errmsg);
		JSONObject taskObj = JSONObject.parseObject(taskData); //��json�ַ���תdataResult��Ϊjson����
		int taskid=taskObj.getInteger("taskid");
		if("0".equals(errcode)){
			String dataApiParam="taskid="+taskid;
			String dataResult=HttpRequestUtil.sendPost(apiUrl, dataApiParam ,apiKey);
			JSONObject dataObj = JSONObject.parseObject(dataResult); //��json�ַ���ת��Ϊjson����
			String rep_errcode=dataObj.getString("errcode");
			String rep_errmsg=dataObj.getString("errmsg");
			log.info(key.getKeywords()+"�������ӿ�2��ѯ״̬��"+rep_errcode+" "+rep_errmsg);
			String rep=dataObj.getString("data");
			int flag=0;
			while (!"0".equals(rep_errcode)) {
				try {
					TimeUnit.MILLISECONDS.sleep(7500);
					flag++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//����
				String result=HttpRequestUtil.sendPost(apiUrl, dataApiParam ,apiKey);
				dataObj = JSONObject.parseObject(result); //��json�ַ���ת��Ϊjson����
				rep_errcode=dataObj.getString("errcode");
				rep_errmsg=dataObj.getString("errmsg");
				log.info(key.getKeywords()+"�������ӿ�2��ѯ״̬��"+rep_errcode+" "+rep_errmsg);
				rep=dataObj.getString("data");
				if("0".equals(rep_errcode)||flag==2){
					break;
				}
			}
			if("0".equals(rep_errcode)){
				JSONObject repObj = JSONObject.parseObject(rep); //��json�ַ���ת��Ϊjson����
				JSONArray keywordmonitorArray=JSONArray.parseArray(repObj.getString("keywordmonitor"));
				JSONObject keyObj = keywordmonitorArray.getJSONObject(0); //��json�ַ���ת��Ϊjson����
				String ranks=keyObj.getString("ranks");
				if(!"[]".equals(ranks)){
					JSONArray ranksObj=JSONArray.parseArray(ranks);
					JSONObject rank=ranksObj.getJSONObject(0);
					if(!"--".equals(key.getChupai())){//Ĭ�ϵ�ֵ
				//		key.setPage_url(rank.getString("page_url"));
						key.setXinpai(rank.getString("rank"));
						String kkstyle=key.getKkstyle();
						String xinpai=key.getXinpai();
						key.setPmbh(dabiao(kkstyle,xinpai)); 
						key.setPmupt_date(date);
						key.setPmstate("1");
					}else{
						key.setChupai(rank.getString("rank"));
				//	key.setPage_url(rank.getString("page_url"));
						key.setPmupt_date(date);
						key.setPmstate("1");
					}
				}else{
					key.setPmstate("0");
					System.out.println("rankΪ[]");
				}
			}else{
				key.setPmstate("0");
			}
			
		}else{
			key.setPmstate("0");
		}
		return key;
	}
	
	public static Keywords getWords(Keywords keywords){
		String webid=keywords.getSearch_engines();
		String keyword=keywords.getKeywords();
		String address=keywords.getDomain_address();
		String apiUrl=null;
		String apiKey=null;
		String apiParam=null;
		if("1".equals(webid)){//baiduPc
			apiUrl=PropertiesUtil.getProperty("baiduPcApiUrl");// ��api�ӿ�url
			apiKey=PropertiesUtil.getProperty("baiduPcApiKey");
			apiParam="keywords="+keyword+"&url="+address;//api���ݵĲ���
		}else if("2".equals(webid)){
			apiUrl=PropertiesUtil.getProperty("baiduMobileApiUrl");// ��api�ӿ�url
			apiKey=PropertiesUtil.getProperty("baiduMobileApiKey");
			apiParam="keywords="+keyword+"&url="+address;//api���ݵĲ���
		}else if("3".equals(webid)){
			apiUrl=PropertiesUtil.getProperty("360PcApiUrl");// ��api�ӿ�url
			apiKey=PropertiesUtil.getProperty("360PcApiKey");
			apiParam="keywords="+keyword+"&url="+address;//api���ݵĲ���
		}
		return PmUtil.getPm(apiUrl, apiKey, apiParam, keywords);
	}
	
	public static String dabiao(String kkstyle,String xinpai){
		
		if("2".equals(kkstyle)){
			if(xinpai.length()==1){
				int x=Integer.parseInt(xinpai);
				if(x<=3){
					//���
					return "1";
				}
			}
		}else if("1".equals(kkstyle)){
			if(xinpai.length()==1||"10".equals(xinpai)){
				int x=Integer.parseInt(xinpai);
				if(x<=10){
					//���
					return "1";
				}
			}
		}
		return "2";
	}
	
}
