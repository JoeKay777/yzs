package cn.com.seo.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.seo.base.utils.DateUtil;
import cn.com.seo.base.utils.EscapeUtil;
import cn.com.seo.base.utils.HttpRequestUtil;
import cn.com.seo.base.utils.JspView;
import cn.com.seo.base.utils.KeyPrice;
import cn.com.seo.base.utils.PmUtil;
import cn.com.seo.base.utils.PropertiesUtil;
import cn.com.seo.base.utils.ReadExcel;
import cn.com.seo.base.utils.WriteExcel;
import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.PmModel;
import cn.com.seo.bean.SeoAdmin;
import cn.com.seo.bean.User;
import cn.com.seo.bean.ZsModel;
import cn.com.seo.service.KeywordsService;
import cn.com.seo.service.LoginService;
import cn.com.seo.vo.api5118.Request;


@Controller
@RequestMapping("/keywords")
public class KeywordsAction {
	public Logger log=Logger.getLogger(KeywordsAction.class);
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
	String date = df.format(new Date());
	@Autowired
	private KeywordsService Kservice;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/keywordsListShow")
	public ModelAndView keywordsListShow(String keywords,String search_engines,String currPage,
			String status,String pxStyle,String pmbh,String pageSize,HttpServletRequest request,Model model){
		User user=(User) request.getSession().getAttribute("user");
		int curr=1;
		if(currPage!=null&&!"".equals(currPage)){
			curr=Integer.parseInt(currPage);
			if(curr<=0){
				curr=1;
			}
		}
		if(pageSize!=null){
			PageHelper.startPage(curr, Integer.parseInt(pageSize));
		}else{
			PageHelper.startPage(curr, 10);
		}
		model.addAttribute("pageSize", pageSize);
		List<Keywords> list=Kservice.KeywordsShowByParam(user.getUsername(), EscapeUtil.unescape(keywords), search_engines,status,pxStyle,pmbh,null,null,null,null);
		PageInfo<Keywords> keyPageList=new PageInfo<Keywords>(list);
		List<Keywords> rowsList=keyPageList.getList();
		int count=(int) keyPageList.getTotal();
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage",curr);
		model.addAttribute("keywords",  EscapeUtil.unescape(keywords));
		model.addAttribute("search_engines", search_engines);
		model.addAttribute("status",status );
		model.addAttribute("pxStyle",pxStyle );
		model.addAttribute("pmbh",pmbh );
		User u=loginService.findById(user.getUser_id());
		model.addAttribute("u", u);
		return new ModelAndView(JspView.KEYWORDSLIST);
	}
	
		@RequestMapping("uploadKeyFile")
	    public String  fileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,Model model) throws IOException {
	    //    long  startTime=System.currentTimeMillis();
	     //   System.out.println("fileName��"+file.getOriginalFilename());
	        String fileName=file.getOriginalFilename();
	        log.info(fileName);
	       /* String path = request.getSession().getServletContext().getRealPath("upload")+"/"+fileName;*/
	        String path=PropertiesUtil.getProperty("keyFileUploadPath")+File.separator+fileName;
	        File newFile=new File(path);
	        if(!newFile.exists()){
	        	newFile.mkdirs();
	        }
	        //ͨ��CommonsMultipartFile�ķ���ֱ��д�ļ���ע�����ʱ��
	        file.transferTo(newFile);
	     
	        String message = null;
	        try {
	         List<List<Map<String, String>>> result=ReadExcel.readExcelWithTitle(path);
	         User user=(User) request.getSession().getAttribute("user");
	         int success=0;
	         int fail=0;
	         int size=0;
	         for (List<Map<String, String>> list : result) {
				for (Map<String, String> map : list) {
					String zsApiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(map.get("�ؼ���"));
					String zsRes=HttpRequestUtil.sendGet(zsApiUrl);
					JSONObject zsStr = JSONObject.parseObject(zsRes); //��json�ַ���ת��Ϊjson����
					String zsData=zsStr.getString("data");//��ȡjson���������ֵ
					int zsStatus=zsStr.getInteger("state");//״̬Ϊ1ʱ ��ȡ�ɹ� �����api�ļ�
					String zsMsg=zsStr.getString("msg");
					ZsModel zs=null;
					Keywords key=new Keywords();
					String map_address=map.get("����").trim();
					String map_keywords=map.get("�ؼ���").trim();
					String map_engines=map.get("��������").trim();
					if(map.get("����")==null&&map_keywords==null&&map_engines==null){
						message="�������ļ�ģ�棡����ģ��¼��ؼ��֣�";
						return JspView.KEYWORDSDR; 
					}
					key.setAdd_date(date);
					key.setUsername(user.getUsername());
					key.setDomain_address(map_address);
					key.setKeywords(map_keywords);
					key.setSearch_engines(map_engines);
					key.setLast_date(date);
			//		key.setPmupt_date(date);
					key.setState("1");
			//		key.setChupai("9999");
					key.setZrpai("9999");
					key.setPage_url("##");
					key.setXinpai("9999");
					key.setPmbh("--");
					key.setRemarks("");
					key.setKkstyle("1");
					key.setCsdj("0");
					if(zsStatus==1){
						zsData=zsData.replace("[", "");
						zsData=zsData.replace("]", "");
						JSONObject zsdata = JSONObject.parseObject(zsData);
						zs=JSONObject.toJavaObject(zsdata, ZsModel.class);
						key.setZsupt_date(date);
						key.setAllIndex(String.valueOf(zs.getSo360index()));
						key.setMobileIndex(String.valueOf(zs.getMobileindex()));
						key.setPrice(KeyPrice.getPrice(zs.getAllindex(), zs.getMobileindex(), zs.getMobileindex(), zs.getKeyword(), map_engines));
						key.setSo360Index(String.valueOf(zs.getSo360index()));
					}else{
						key.setAllIndex("--");
						key.setMobileIndex("--");
						key.setSo360Index("--");
						log.info(map_keywords+"��ָ����ѯ�ӿڷ������������"+zsMsg);
					//	String err_msg="�ӿ�ά���У���ʱ���������ؼ��֣������������µ�ͷ���";
					//	model.addAttribute("err_msg", err_msg);
					//	break;
					}
					int save=Kservice.save(key,  map_address, map_keywords,map_engines);
					if(save==0){
						fail++;
					}else{
						success++;
					}
				}
				size=size+list.size();
			}
	         log.info(date+"�����ؼ�������:"+success);
	         message="������"+size+"��,����ɹ���"+success+"��,ʧ�ܣ�"+fail+"��(֮ǰ��¼��)";
	         log.info(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        model.addAttribute("msg", message);
			User user=(User) request.getSession().getAttribute("user");
	    	User u=loginService.findById(user.getUser_id());
			model.addAttribute("u", u);
	    //    long  endTime=System.currentTimeMillis();
	    //    System.out.println("����ʱ�䣺"+String.valueOf(endTime-startTime)+"ms");
	        return JspView.KEYWORDSDR; 
	    }
	
		@RequestMapping(value="/exportCheckKeys",produces="text/html;charset=UTF-8;")  
	    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
			String date = df.format(new Date());
			List<Keywords> keyList=Kservice.KeywordsShowByParam(null, null, null,"1",null,null,null,null,null,null);
			String fileName=date+".xlsx";
			String filePath=PropertiesUtil.getProperty("keyCheckDownPath");
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdirs();
				/*request.setAttribute("fileMsg", "0");
				return;*/
			}
			FileOutputStream out = new FileOutputStream(filePath + File.separator + fileName);
			WriteExcel.expertCheckKeys(keyList, out, date);
		    //����ļ�������
	         //������Ӧͷ��������������ظ��ļ�
	        response.setContentType("application/x-excel");
	        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
	        FileInputStream in = new FileInputStream(filePath + File.separator + fileName);
	        OutputStream os = response.getOutputStream();
	         //���û�����
	        byte[] bytes = new byte[1024];
	        int len = 0;
	        while((len = in.read(bytes))>0){
	            os.write(bytes);
	        }
	        in.close();
	        os.close();
	    }  
		
		@RequestMapping("/downModel")  
	    public void downModel(HttpServletRequest request,HttpServletResponse response) throws Exception{
			 		String fileName = PropertiesUtil.getProperty("modelFileName");
			        String path = PropertiesUtil.getProperty("modelFilePath");
			        File file = new File(path+File.separator+fileName);
			       //����ļ�������
			        if(!file.exists()){
			        	file.mkdirs();
			           request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������");
			           return;
			        }
			         //������Ӧͷ��������������ظ��ļ�
			        response.setContentType("application/x-excel");
			        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			        FileInputStream in = new FileInputStream(path + File.separator + fileName);
			        OutputStream os = response.getOutputStream();
			         //���û�����
			        byte[] bytes = new byte[1024];
			        int len = 0;
			        while((len = in.read(bytes))>0){
			            os.write(bytes);
			        }
			        in.close();
			        os.close();

		}  
		
		@ResponseBody
		@RequestMapping("/keysPrice")
		public Object keysPrice(String keyArray){//��ѯ�ؼ��ּ۸�
			List<Map<String, String>> list=new ArrayList<Map<String,String>>();
				String zsapiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(keyArray);;//ָ����api�ӿ�url
				String zsResult=HttpRequestUtil.sendGet(zsapiUrl);
				JSONObject jsStr = JSONObject.parseObject(zsResult); //��json�ַ���ת��Ϊjson����
				String data=jsStr.getString("data");//��ȡjson���������ֵ
				int status=jsStr.getInteger("state");//״̬Ϊ1ʱ ��ȡ�ɹ� �����api�ļ�
				String msg=jsStr.getString("msg");
				log.info("ָ����ѯ��״̬��--------"+msg);
				if(status==1){
					JSONArray jsonArray=JSONArray.parseArray(data);
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jsonObject=jsonArray.getJSONObject(i);
						ZsModel zs = (ZsModel) JSONObject.toJavaObject(jsonObject,ZsModel.class);//json����ת��Ϊ�����
						String[] price=KeyPrice.getAllPrice(zs.getAllindex(), zs.getMobileindex(), zs.getSo360index(), zs.getKeyword());
						Map<String, String> map=new HashMap<String, String>();
						map.put("baiduPcPrice", price[0]);
						map.put("baiduMoPrice", price[1]);
						map.put("so360Price", price[2]);
						map.put("keyword", zs.getKeyword());
						list.add(map);
					}
				}else{
					log.info("ָ����ѯ��״̬��--------"+msg);
				}
				
			return list;
		}
		
		@ResponseBody
		@RequestMapping(value="/loadKeywords",produces="text/html;charset=UTF-8;")
		public String loadKeywords(String username,String address,String webid,String keyArray,HttpSession session){
			User user=(User) session.getAttribute("user");
			String user_name=null;
			if(user!=null){
				user_name=user.getUsername();
			}else{
				user_name=username;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
			String date = df.format(new Date());
			String[] keys=keyArray.split(",");
			int success=0;
			int fail=0;
			int save=0;
			for (String keyword : keys) {
				String zsapiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(keyword);;//ָ����api�ӿ�url
				String zsResult=HttpRequestUtil.sendGet(zsapiUrl);
				JSONObject jsStr = JSONObject.parseObject(zsResult); //��json�ַ���ת��Ϊjson����
				String data=jsStr.getString("data");//��ȡjson���������ֵ
				int status=jsStr.getInteger("state");//״̬Ϊ1ʱ ��ȡ�ɹ� �����api�ļ�
				String msg=jsStr.getString("msg");
				log.info("ָ����ѯ��״̬��--------"+msg);
				Keywords key=new Keywords();
				key.setAdd_date(date);
				key.setDomain_address(address.trim());
				key.setKeywords(keyword.trim());
				key.setLast_date(date);
			//	key.setPmupt_date(date);
				key.setSearch_engines(webid.trim());
				key.setState("1");//�����
				key.setTran_money(BigDecimal.valueOf(0.00));
				key.setUsername(user_name);
			//	key.setChupai("9999");
				key.setZrpai("9999");
				key.setPage_url("##");
				key.setXinpai("9999");
				key.setPmbh("--");
				key.setRemarks("");
				key.setKkstyle("1");
				key.setCsdj("0");
				if(status==1){
					JSONArray jsonArray=JSONArray.parseArray(data);
					JSONObject jsonObject=jsonArray.getJSONObject(0);
					ZsModel zs = (ZsModel) JSONObject.toJavaObject(jsonObject,ZsModel.class);//json����ת��Ϊ�����
					key.setZsupt_date(date);
					key.setAllIndex(String.valueOf(zs.getAllindex()));
					key.setMobileIndex(String.valueOf(zs.getMobileindex()));
					key.setPrice(KeyPrice.getPrice(zs.getAllindex(), zs.getMobileindex(), zs.getMobileindex(), zs.getKeyword(), webid));
					key.setSo360Index(String.valueOf(zs.getSo360index()));
				}else{
					key.setAllIndex("--");
					key.setMobileIndex("--");
					key.setSo360Index("--");
					log.info("ָ���ӿڷ������������"+msg);
				//	String message="�ӿ�ά���У���ʱ���������ؼ��֣������������µ�ͷ���";
				//	return message;
				}
				save=Kservice.save(key,address.trim(),keyword.trim(),webid.trim());
				if(save==0){
					fail++;
					continue;
				}else{
					success++;
				}
			}
			log.info(date+"�����ؼ�������:"+success);
			String message="������"+keys.length+"��,�ɹ���"+success+"��,ʧ�ܣ�"+fail+"��(֮ǰ��¼��)";
			return message;
			
		}
		public int change(int chupai,int xinpai){
			int change=xinpai-chupai;
			return change;
		}
		
		@ResponseBody
		@RequestMapping("/del")
		public String delKeywords(String keywords_id,HttpSession session){
			User user=(User) session.getAttribute("user");
			SeoAdmin admin=(SeoAdmin)session.getAttribute("admin");
			String name=null;
			if(user==null){
				name=admin.getAdmin_name();
			}else{
				name=user.getUsername();
			}
			int id=Integer.parseInt(keywords_id);
			Keywords keywords=Kservice.keywordsById(id);
		//	int del=Kservice.delete(id);
			keywords.setDeldate(date);
			keywords.setState("5");
			int del=Kservice.uptKeyword(keywords);//ɾ��������վ
			String msg="";
			if(del!=0){
				msg="1";
				log.info(name+"ɾ���ؼ��ֵ�����վ��"+keywords.getKeywords()+keywords.getDomain_address());
			}else{
				msg="0";
				log.info(name+"ɾ���ؼ��ֵ�����վʧ�ܣ�"+keywords.getKeywords()+keywords.getDomain_address());
			}
			return msg;
		}
		
		@ResponseBody
		@RequestMapping("/uptKeywordsStatus")
		public int uptKeywordsStatus(String keywords_id,String status,HttpSession session){
			int id=Integer.parseInt(keywords_id);
			int upt=Kservice.update(id,status);
			return upt;
		}
		
		
}
