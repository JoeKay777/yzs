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
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
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
	     //   System.out.println("fileName："+file.getOriginalFilename());
	        String fileName=file.getOriginalFilename();
	        log.info(fileName);
	       /* String path = request.getSession().getServletContext().getRealPath("upload")+"/"+fileName;*/
	        String path=PropertiesUtil.getProperty("keyFileUploadPath")+File.separator+fileName;
	        File newFile=new File(path);
	        if(!newFile.exists()){
	        	newFile.mkdirs();
	        }
	        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
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
					String zsApiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(map.get("关键字"));
					String zsRes=HttpRequestUtil.sendGet(zsApiUrl);
					JSONObject zsStr = JSONObject.parseObject(zsRes); //把json字符串转换为json对象
					String zsData=zsStr.getString("data");//获取json对象的属性值
					int zsStatus=zsStr.getInteger("state");//状态为1时 获取成功 详情见api文件
					String zsMsg=zsStr.getString("msg");
					ZsModel zs=null;
					Keywords key=new Keywords();
					String map_address=map.get("域名").trim();
					String map_keywords=map.get("关键字").trim();
					String map_engines=map.get("搜索类型").trim();
					if(map.get("域名")==null&&map_keywords==null&&map_engines==null){
						message="请下载文件模版！按照模版录入关键字！";
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
						log.info(map_keywords+"，指数查询接口返回数据情况："+zsMsg);
					//	String err_msg="接口维护中，暂时不能新增关键字，如有疑问请致电客服。";
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
	         log.info(date+"新增关键字总数:"+success);
	         message="共导入"+size+"条,导入成功："+success+"条,失败："+fail+"条(之前已录入)";
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
	    //    System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
	        return JspView.KEYWORDSDR; 
	    }
	
		@RequestMapping(value="/exportCheckKeys",produces="text/html;charset=UTF-8;")  
	    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
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
		    //如果文件不存在
	         //设置响应头，控制浏览器下载该文件
	        response.setContentType("application/x-excel");
	        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
	        FileInputStream in = new FileInputStream(filePath + File.separator + fileName);
	        OutputStream os = response.getOutputStream();
	         //设置缓存区
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
			       //如果文件不存在
			        if(!file.exists()){
			        	file.mkdirs();
			           request.setAttribute("message", "您要下载的资源已被删除！！");
			           return;
			        }
			         //设置响应头，控制浏览器下载该文件
			        response.setContentType("application/x-excel");
			        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			        FileInputStream in = new FileInputStream(path + File.separator + fileName);
			        OutputStream os = response.getOutputStream();
			         //设置缓存区
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
		public Object keysPrice(String keyArray){//查询关键字价格
			List<Map<String, String>> list=new ArrayList<Map<String,String>>();
				String zsapiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(keyArray);;//指数的api接口url
				String zsResult=HttpRequestUtil.sendGet(zsapiUrl);
				JSONObject jsStr = JSONObject.parseObject(zsResult); //把json字符串转换为json对象
				String data=jsStr.getString("data");//获取json对象的属性值
				int status=jsStr.getInteger("state");//状态为1时 获取成功 详情见api文件
				String msg=jsStr.getString("msg");
				log.info("指数查询的状态：--------"+msg);
				if(status==1){
					JSONArray jsonArray=JSONArray.parseArray(data);
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jsonObject=jsonArray.getJSONObject(i);
						ZsModel zs = (ZsModel) JSONObject.toJavaObject(jsonObject,ZsModel.class);//json对象转换为类对象
						String[] price=KeyPrice.getAllPrice(zs.getAllindex(), zs.getMobileindex(), zs.getSo360index(), zs.getKeyword());
						Map<String, String> map=new HashMap<String, String>();
						map.put("baiduPcPrice", price[0]);
						map.put("baiduMoPrice", price[1]);
						map.put("so360Price", price[2]);
						map.put("keyword", zs.getKeyword());
						list.add(map);
					}
				}else{
					log.info("指数查询的状态：--------"+msg);
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
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
			String date = df.format(new Date());
			String[] keys=keyArray.split(",");
			int success=0;
			int fail=0;
			int save=0;
			for (String keyword : keys) {
				String zsapiUrl=PropertiesUtil.getProperty("zsApiUrl")+"?key="+PropertiesUtil.getProperty("zsApiKey")+"&kws="+EscapeUtil.escape(keyword);;//指数的api接口url
				String zsResult=HttpRequestUtil.sendGet(zsapiUrl);
				JSONObject jsStr = JSONObject.parseObject(zsResult); //把json字符串转换为json对象
				String data=jsStr.getString("data");//获取json对象的属性值
				int status=jsStr.getInteger("state");//状态为1时 获取成功 详情见api文件
				String msg=jsStr.getString("msg");
				log.info("指数查询的状态：--------"+msg);
				Keywords key=new Keywords();
				key.setAdd_date(date);
				key.setDomain_address(address.trim());
				key.setKeywords(keyword.trim());
				key.setLast_date(date);
			//	key.setPmupt_date(date);
				key.setSearch_engines(webid.trim());
				key.setState("1");//待审核
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
					ZsModel zs = (ZsModel) JSONObject.toJavaObject(jsonObject,ZsModel.class);//json对象转换为类对象
					key.setZsupt_date(date);
					key.setAllIndex(String.valueOf(zs.getAllindex()));
					key.setMobileIndex(String.valueOf(zs.getMobileindex()));
					key.setPrice(KeyPrice.getPrice(zs.getAllindex(), zs.getMobileindex(), zs.getMobileindex(), zs.getKeyword(), webid));
					key.setSo360Index(String.valueOf(zs.getSo360index()));
				}else{
					key.setAllIndex("--");
					key.setMobileIndex("--");
					key.setSo360Index("--");
					log.info("指数接口返回数据情况："+msg);
				//	String message="接口维护中，暂时不能新增关键字，如有疑问请致电客服。";
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
			log.info(date+"新增关键字总数:"+success);
			String message="共导入"+keys.length+"条,成功："+success+"条,失败："+fail+"条(之前已录入)";
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
			int del=Kservice.uptKeyword(keywords);//删除到回收站
			String msg="";
			if(del!=0){
				msg="1";
				log.info(name+"删除关键字到回收站："+keywords.getKeywords()+keywords.getDomain_address());
			}else{
				msg="0";
				log.info(name+"删除关键字到回收站失败："+keywords.getKeywords()+keywords.getDomain_address());
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
