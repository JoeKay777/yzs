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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.seo.base.utils.CreateTxt;
import cn.com.seo.base.utils.DateUtil;
import cn.com.seo.base.utils.EscapeUtil;
import cn.com.seo.base.utils.HttpRequestUtil;
import cn.com.seo.base.utils.KeyPrice;
import cn.com.seo.base.utils.PmUtil;
import cn.com.seo.base.utils.PropertiesUtil;
import cn.com.seo.base.utils.ReadExcel;
import cn.com.seo.base.utils.WriteExcel;
import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.Rechange;
import cn.com.seo.bean.SeoAdmin;
import cn.com.seo.bean.TranDetail;
import cn.com.seo.bean.TranSerial;
import cn.com.seo.bean.User;
import cn.com.seo.bean.ZsModel;
import cn.com.seo.service.AdminService;
import cn.com.seo.service.KeywordsService;
import cn.com.seo.service.LoginService;
import cn.com.seo.service.MoneyService;

@Controller
@RequestMapping("/admin")
public class AdminAction {
	public Logger log = Logger.getLogger(AdminAction.class);
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
	String today = df.format(new Date());
	@Autowired
	private AdminService aService;
	@Autowired
	private LoginService lService;
	@Autowired
	private KeywordsService kService;
	@Autowired
	private MoneyService mService;

	@ResponseBody
	@RequestMapping(value = "/adminAdd", produces = "text/html;charset=UTF-8;")
	public String adminAdd(SeoAdmin admin) {
		admin.setAdmin_adddate(today);
		String msg = aService.insertAdmin(admin);
		return msg;
	}

	@RequestMapping("/add")
	public String add() {
		return "admin/admin_adminadd";
	}

	@RequestMapping(value = "/detail", produces = "text/html;charset=UTF-8;")
	public String detail(String admin_id, Model model) {
		SeoAdmin seoadmin = aService.selById(Integer.parseInt(admin_id));
		model.addAttribute("a", seoadmin);
		return "admin/admin_admindetail";
	}

	@RequestMapping("/list")
	public String list(String currPage, Model model) {
		int curr = 1;
		if (currPage != null && !"".equals(currPage)) {
			curr = Integer.parseInt(currPage);
			if (curr <= 0) {
				curr = 1;
			}
		}
		PageHelper.startPage(curr, 10);
		List<SeoAdmin> adminList = aService.selAdminList(null);
		PageInfo<SeoAdmin> pageList = new PageInfo<SeoAdmin>(adminList);
		List<SeoAdmin> rowsList = pageList.getList();
		int count = (int) pageList.getTotal();
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		return "admin/admin_adminlist";
	}

	@ResponseBody
	@RequestMapping(value = "/uptPwd", produces = "text/html;charset=UTF-8;")
	public String uptpwd(String oldPwd, String newPwd, HttpSession session) {
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		String msg = aService.uptPwd(oldPwd, newPwd, admin.getAdmin_id());
		return msg;
	}

	@RequestMapping("/adminUptPwd")
	public String adminUptPwd() {
		return "admin/admin_adminpwd";
	}

	@ResponseBody
	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8;")
	public String update(String admin_id, String admin_mail, String admin_tel, String admin_truename, String admin_qq) {
		int adminId = Integer.parseInt(admin_id);
		SeoAdmin admin = new SeoAdmin(adminId, admin_truename, admin_tel, admin_mail, admin_qq);
		String msg = aService.updateAdmin(admin);
		return msg;
	}

	@RequestMapping("/upt")
	public String upt(HttpSession session, Model model) {
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		if (admin != null) {
			SeoAdmin seoadmin = aService.selById(admin.getAdmin_id());
			model.addAttribute("a", seoadmin);
		}
		return "admin/admin_adminupt";
	}

	@ResponseBody
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8;")
	public String login(String name, String pass, HttpSession session) {
		session.setAttribute("admin", aService.selByName(name));
		String msg = aService.selByNamePass(name, pass);
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/del", produces = "text/html;charset=UTF-8;")
	public String del(String admin_id) {
		String msg = aService.delAdmin(Integer.parseInt(admin_id));
		return msg;
	}

	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		log.info("退出登录，清除session");
		return "admin/login";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		String yesterday = DateUtil.getYesterday();
		int userCount = lService.findAll(null, null, null).size();
		int wczCount = lService.findAll(null, null, "0").size();
		int yebzCount = lService.findAll(null, null, "2").size();
		int dabiaoCount = mService.tranList(0, yesterday, null, "1").size();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
		String date = df.format(new Date());
		BigDecimal yesterdayAccount = mService.getAccount(yesterday.substring(6, 8), yesterday.substring(4, 6),
				yesterday.substring(0, 4), null);
		if (yesterdayAccount == null) {
			yesterdayAccount = BigDecimal.valueOf(0.0);
		}
		int keyAddCount = kService.keywordsCount(date, null, null);
		int checkCount = kService.KeywordsShowByParam(null, null, null, "1", null, null,null,null,null,null).size();
		model.addAttribute("userCount", userCount);
		model.addAttribute("dabiaoCount", dabiaoCount);
		model.addAttribute("yesterdayAccount", yesterdayAccount);
		model.addAttribute("keyAddCount", keyAddCount);
		model.addAttribute("wczCount", wczCount);
		model.addAttribute("yebzCount", yebzCount);
		model.addAttribute("checkCount", checkCount);
		return "admin/admin_index";
	}


	@ResponseBody
	@RequestMapping(value = "/uptPrice")
	public int uptPrice(String keywords_id,String price){
		int id=Integer.parseInt(keywords_id);
		BigDecimal key_price=BigDecimal.valueOf(Integer.parseInt(price));
		Keywords words=new Keywords();
		words.setKeywords_id(id);
		words.setPrice(key_price);
		int upt=kService.uptKeyword(words);
		return upt;
	}
	@ResponseBody
	@RequestMapping("/uptChupai")
	public int uptChupai(String keywords_id,String chupai){
		int id=Integer.parseInt(keywords_id);
		Keywords words=new Keywords();
		words.setKeywords_id(id);
		words.setChupai(chupai);
		words.setPmupt_date(DateUtil.getToday());
		words.setPmstate("1");
		int upt=kService.uptKeyword(words);
		return upt;
	}
	@ResponseBody
	@RequestMapping("/uptXinpai")
	public int uptXinpai(String keywords_id,String xinpai){
		int id=Integer.parseInt(keywords_id);
		Keywords words=kService.keywordsById(id);
		String kkstyle=words.getKkstyle();
		String dabiao=PmUtil.dabiao(kkstyle, xinpai);
		Keywords keywords=new Keywords();
		keywords.setKeywords_id(id);
		keywords.setXinpai(xinpai);
		keywords.setPmbh(dabiao);
		keywords.setPmupt_date(DateUtil.getToday());
		keywords.setPmstate("1");
		int upt=kService.uptKeyword(keywords);
		return upt;
	}
	
	@ResponseBody
	@RequestMapping("/uptRemarks")
	public int uptRemarks(String keywords_id,String remarks){
		int id=Integer.parseInt(keywords_id);
		Keywords words=new Keywords();
		words.setKeywords_id(id);
		words.setRemarks(remarks);
		int upt=kService.uptKeyword(words);
		return upt;
	}
	
	@ResponseBody
	@RequestMapping("/uptDomain")
	public int uptDomain(String keywords_id,String domain){
		int id=Integer.parseInt(keywords_id);
		Keywords words=new Keywords();
		words.setKeywords_id(id);
		words.setDomain_address(domain);
		int upt=kService.uptKeyword(words);
		return upt;
	}
	@ResponseBody
	@RequestMapping("/uptStyle")
	public int uptKkstyle(String keywords_id,String kkstyle){
		int id=Integer.parseInt(keywords_id);
		Keywords words=new Keywords();
		words.setKeywords_id(id);
		words.setKkstyle(kkstyle);
		int upt=kService.uptKeyword(words);
		return upt;
	}
	@ResponseBody
	@RequestMapping("/uptCsdj")
	public int uptCsdj(String keywords_id,String csdj){
		int id=Integer.parseInt(keywords_id);
		Keywords words=new Keywords();
		words.setKeywords_id(id);
		words.setCsdj(csdj);
		int upt=kService.uptKeyword(words);
		return upt;
	}
	/*
	@ResponseBody
	@RequestMapping(value = "/batchUpt", produces = "text/html;charset=UTF-8;")
	public String batchUptWords(String uptList, HttpSession session) {
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		JSONArray list = JSONArray.parseArray(uptList);
		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			JSONObject uptObj = list.getJSONObject(i); // 把json字符串转换为json对象
			int keywords_id = Integer.parseInt(uptObj.getString("keywords_id"));
			String domain = uptObj.getString("domain");
			int zs = Integer.parseInt(uptObj.getString("zs"));
			String price = uptObj.getString("price");
			String chupai = uptObj.getString("chupai");
			String xinpai = uptObj.getString("xinpai");
			String dabiao = PmUtil.dabiao(chupai, xinpai);
			String remarks = uptObj.getString("remarks");
			int upt = kService.uptKeyword(keywords_id, domain, zs, price, chupai, xinpai, dabiao, remarks);
			if (upt != 0) {
				num++;
				log.info(admin.getAdmin_name() + "修改关键字" + keywords_id + "成功");
			}
		}
		if (num == list.size()) {
			String upt_msg = num + "条数据修改成功";
			return upt_msg;
		}
		return "系统出错";
	}*/
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/upt",produces="text/html;charset=UTF-8;") public
	 * String upt(S){
	 * 
	 * return ""; }
	 */

	@RequestMapping("/keyadd")
	public String keyadd(Model model) {
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		return "admin/admin_keyadd";
	}

	@RequestMapping("/keyprice")
	public String keyprice() {
		return "admin/admin_keyprice";
	}

	@ResponseBody
	@RequestMapping("/del")
	public String delKeywords(String keywords_id, HttpSession session) {
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		int id = Integer.parseInt(keywords_id);
		Keywords keywords = kService.keywordsById(id);
		int del = kService.delete(id);
		String msg = "";
		if (del != 0) {
			msg = "1";
			log.info(admin.getAdmin_name() + "删除关键字：" + keywords.getKeywords() + keywords.getDomain_address());
		} else {
			msg = "0";
			log.info(keywords_id + "，删除失败");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/batchDel", produces = "text/html;charset=UTF-8;")
	public String batchDelWords(String delList, HttpSession session) {
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		JSONArray list = JSONArray.parseArray(delList);
		int num = 0;
		Keywords words=new Keywords();
		for (int i = 0; i < list.size(); i++) {
			JSONObject delObj = list.getJSONObject(i); // 把json字符串转换为json对象
			int keywords_id = Integer.parseInt(delObj.getString("keywords_id"));
			String keywords = delObj.getString("keywords");
			//int del = kService.delete(keywords_id);
			words.setDeldate(DateUtil.getToday());
			words.setKeywords_id(keywords_id);
			words.setState("5");
			int del=kService.uptKeyword(words);//回收到回收站
			if (del != 0) {
				num++;
				log.info(admin.getAdmin_name() + "删除关键字到回收站" + keywords + "成功");
			}
		}
		if (num == list.size()) {
			String del_msg = num + "条数据删除成功，找回数据请到回收站";
			return del_msg;
		}
		return "系统出错";
	}

	@RequestMapping(value = "/exportKeysTXT", produces = "text/html;charset=UTF-8;")
	public void exportKeysTXT(String username, String keywords, String search_engines, String status,
			String pxStyle, String pmbh,String chupai,String xinpai,String pmstate, String csdj,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Keywords> keyList = kService.KeywordsShowByParam(EscapeUtil.unescape(username),
				EscapeUtil.unescape(keywords), search_engines, status, pxStyle, pmbh,chupai,xinpai,pmstate,csdj);
		String filePath = PropertiesUtil.getProperty("keysDownPath");
		String fileName = "关键字" + DateUtil.getToday() + ".xlsx";
		FileOutputStream out = new FileOutputStream(filePath + File.separator + fileName);
		WriteExcel.exportKeywordsExcel(keyList, out);
	/*	StringBuffer str = new StringBuffer();
		for (Keywords word : keyList) {
			str.append(word.getKeywords() + word.getDomain_address());
			str.append("\r\n");
		}
		CreateTxt.writeTxtFile(str, filePath +File.separator + fileName);
*/		// 如果文件不存在
		// 设置响应头，控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		FileInputStream in = new FileInputStream(filePath + File.separator + fileName);
		OutputStream os = response.getOutputStream();
		// 设置缓存区
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = in.read(bytes)) > 0) {
			os.write(bytes);
		}
		in.close();
		os.close();
	}

	@RequestMapping("/keylist")
	public String keylist(String username, String keywords, String search_engines, String currPage, String status,
			String pxStyle, String pmbh,String chupai,String xinpai,String pmstate, String pageSize,String csdj,HttpServletRequest request, Model model) {
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		int curr = 1;
		if (currPage != null && !"".equals(currPage)) {
			curr = Integer.parseInt(currPage);
			if (curr <= 0) {
				curr = 1;
			}
		}
		if(pageSize!=null){
			PageHelper.startPage(curr, Integer.parseInt(pageSize));
		}else{
			PageHelper.startPage(curr, 10);
		}
		model.addAttribute("pageSize", pageSize);
		List<Keywords> list = kService.KeywordsShowByParam(EscapeUtil.unescape(username), EscapeUtil.unescape(keywords),
				search_engines, status, pxStyle, pmbh,chupai,xinpai,pmstate,csdj);
		PageInfo<Keywords> keyPageList = new PageInfo<Keywords>(list);
		List<Keywords> rowsList = keyPageList.getList();
		int count = (int) keyPageList.getTotal();
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("username", EscapeUtil.unescape(username));
		model.addAttribute("keywords", EscapeUtil.unescape(keywords));
		model.addAttribute("search_engines", search_engines);
		model.addAttribute("status", status);
		model.addAttribute("pxStyle", pxStyle);
		model.addAttribute("chupai", chupai);
		model.addAttribute("xinpai", xinpai);
		model.addAttribute("pmstate", pmstate);
		model.addAttribute("csdj", csdj);
		model.addAttribute("pmbh", pmbh);
		return "admin/admin_keylist";
	}

	@RequestMapping(value = "/loadKeyPm", produces = "text/html;charset=UTF-8;")
	public String loadKeyPm(@RequestParam("file") MultipartFile  file, HttpServletRequest request, 
			HttpSession session,Model model) throws Exception {
		SeoAdmin admin=(SeoAdmin) session.getAttribute("admin");
		String fileName = file.getOriginalFilename();
		String path = PropertiesUtil.getProperty("keyFileUploadPath") + File.separator + fileName;
		File newFile = new File(path);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		String message = null;
		List<List<Map<String, String>>> result = ReadExcel.readExcelWithTitle(path);
		int success = 0;
		int fail = 0;
		int fail1 = 0;
		int size = 0;
		for (List<Map<String, String>> list : result) {
			for (Map<String, String> map : list) {
				String keywords = map.get("关键词");
				if(keywords==null||"".equals(keywords)){
					continue;
				}
				String address = map.get("网址").replace("http://", "").trim();
				String engines = map.get("搜索引擎").trim();
				String search_engines = null;
				if ("百度".equals(engines)) {
					search_engines = "1";
				} else if ("手机百度".equals(engines)) {
					search_engines = "2";
				} else if ("360".equals(engines)) {
					search_engines = "3";
				}
				String pm = map.get("排名");
				Keywords words = kService.KeywordsBySerKey(keywords, search_engines, address);
				if(pm!=null&&!"-".equals(pm)){
					if(words!=null){
						/*if ("9999".equals(words.getChupai())) {
							words.setChupai(pm);
							words.setPmupt_date(DateUtil.getToday());
							words.setPmstate("1");
						} else {*/
							words.setXinpai(pm);
							String dabiao = PmUtil.dabiao(words.getKkstyle(), words.getXinpai());
							words.setPmbh(dabiao);
							words.setPmupt_date(DateUtil.getToday());
							words.setPmstate("1");
					//	}
						int upt = kService.pmUpdate(words);
						if (upt != 0) {
							success++;
						}
					}else{
						fail1++;
						continue;
					}
				}else{
					if(words!=null){
						/*if ("9999".equals(words.getChupai())) {
							words.setChupai("10000");
							words.setPmupt_date(DateUtil.getToday());
							words.setPmstate("1");
						} else {*/
							words.setXinpai("10000");
							String dabiao = PmUtil.dabiao(words.getKkstyle(), words.getXinpai());
							words.setPmbh(dabiao);
							words.setPmupt_date(DateUtil.getToday());
							words.setPmstate("1");
						//}
					}else{
						fail1++;
						continue;
					}
					kService.pmUpdate(words);
					fail++;
					continue;
				}
			}
			size+=list.size();
		}
		message="共导入"+size+"条，成功加载"+success+"条，失败"+(fail+fail1)+"条(引擎未录入"+fail+"条，关键词未录入"+fail1+"条)";
		model.addAttribute("pmmsg", message);
		log.info(admin.getAdmin_name()+message);
		return "admin/admin_keylist";
	}

	
	@RequestMapping(value = "/loadKeyCsdj", produces = "text/html;charset=UTF-8;")
	public String loadKeyCsdj(@RequestParam("file") MultipartFile  file, HttpServletRequest request, 
			HttpSession session,Model model) throws Exception {
		SeoAdmin admin=(SeoAdmin) session.getAttribute("admin");
		String fileName = file.getOriginalFilename();
		String path = PropertiesUtil.getProperty("keyFileUploadPath") + File.separator + fileName;
		File newFile = new File(path);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		String message = null;
		List<List<Map<String, String>>> result = ReadExcel.readExcelWithTitle(path);
		int success = 0;
		int fail = 0;
		int size = 0;
		for (List<Map<String, String>> list : result) {
			for (Map<String, String> map : list) {
				String keywords = map.get("关键词");
				if(keywords==null||"".equals(keywords)){
					continue;
				}
				String address = map.get("域名").replace("http://", "").trim();
				String engines = map.get("搜索引擎").trim();
				String search_engines = null;
				if ("百度PC".equals(engines)) {
					search_engines = "1";
				} else if ("百度Mobile".equals(engines)) {
					search_engines = "2";
				} else if ("360PC".equals(engines)) {
					search_engines = "3";
				}
				Keywords words = kService.KeywordsBySerKey(keywords, search_engines, address);
				if(words!=null){
					words.setCsdj("1");
					kService.uptKeyword(words);
					success++;
				}else{
					fail++;
				}
			}
			size+=list.size();
		}
		message="共导入"+size+"条，成功加载"+success+"条，失败"+fail+"条(关键词未录入)";
		model.addAttribute("pmmsg", message);
		log.info(admin.getAdmin_name()+message);
		return "admin/admin_keylist";
	}
	@RequestMapping("/keychecklist")
	public String keychecklist(String username, String currPage, String search_engines, String qyFlag,String pageSize, Model model,
			HttpSession session) {
		int curr = 1;
		if (currPage != null && !"".equals(currPage)) {
			curr = Integer.parseInt(currPage);
			if (curr <= 0) {
				curr = 1;
			}
		}
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		Keywords words = null;
		if (qyFlag != null) {
			int qycount = 0;
			List<Keywords> list = kService.KeywordsShowByParam(username, null, search_engines, "1", null, null,null,null,null,null);
			for (Keywords keywords : list) {
				int upt = kService.update(keywords.getKeywords_id(), "2");
				if (upt != 0) {
					qycount++;
					// words=PmUtil.getWords(keywords);
					// if(words!=null){
					kService.uptParam(words);
					// }
				}
			}
			log.info(admin.getAdmin_name() + "启用关键字" + qycount + "条");
			model.addAttribute("qycount", qycount);
		}
		if(pageSize!=null){
			PageHelper.startPage(curr, Integer.parseInt(pageSize));
		}else{
			PageHelper.startPage(curr, 10);
		}
		model.addAttribute("pageSize", pageSize);
		List<Keywords> keysList = kService.KeywordsShowByParam(username, null, search_engines, "1", null, null,null,null,null,null);
		PageInfo<Keywords> keyPageList = new PageInfo<Keywords>(keysList);
		int count = (int) keyPageList.getTotal();
		List<Keywords> rowsList = keyPageList.getList();
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("rows", rowsList);
		model.addAttribute("username", EscapeUtil.unescape(username));
		model.addAttribute("search_engines", search_engines);
		return "admin/admin_keycheck";
	}

	@RequestMapping("/rechangelist")
	public String rechangelist(String username, String payment_style, String date, String currPage, String pageSize,Model model,
			HttpSession session) {

		int curr = 1;
		if (currPage != null) {
			curr = Integer.parseInt(currPage);
			if (curr <= 0) {
				curr = 1;
			}
		}
		if ("".equals(date)) {
			date = null;
		}
		if(pageSize!=null){
			PageHelper.startPage(curr, Integer.parseInt(pageSize));
		}else{
			PageHelper.startPage(curr, 10);
		}
		model.addAttribute("pageSize", pageSize);
		List<Rechange> list = mService.rechangeList(username, date, payment_style);
		BigDecimal sum_amount = mService.getAmount(username, date, payment_style);
		PageInfo<Rechange> pageList = new PageInfo<Rechange>(list);
		List<Rechange> rowsList = pageList.getList();
		int count = (int) pageList.getTotal();
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("username", EscapeUtil.unescape(username));
		model.addAttribute("payment_style", payment_style);
		model.addAttribute("date", date);
		if (sum_amount == null) {
			sum_amount = BigDecimal.valueOf(0.0);
		}
		model.addAttribute("sum_amount", sum_amount);
		return "admin/admin_rechangelist";

	}

	@RequestMapping("/rechangeadd")
	public String rechangeadd(Model model) {
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		return "admin/admin_rechangeadd";
	}

	@RequestMapping("/transeriallist")
	public String transeriallist(String date, String currPage,String pageSize, Model model, String username) {

		int curr = 1;
		if (currPage != null) {
			curr = Integer.parseInt(currPage);
			if (curr <= 0) {
				curr = 1;
			}
		}
		if (date == null) {
			date = DateUtil.getYesterday();
		}
		BigDecimal sum_account = null;
		if(pageSize!=null){
			PageHelper.startPage(curr, Integer.parseInt(pageSize));
		}else{
			PageHelper.startPage(curr, 10);
		}
		model.addAttribute("pageSize", pageSize);
		List<TranSerial> list = new ArrayList<TranSerial>();
		if (date == null || "".equals(date)) {
			list = mService.allTranSerial(username, null, null, null);
			sum_account = mService.getAccount(null, null, null, username);
		} else {
			if (date.length() == 8) {
				list = mService.allTranSerial(username, date.substring(6, 8), date.substring(4, 6),
						date.substring(0, 4));
				sum_account = mService.getAccount(date.substring(6, 8), date.substring(4, 6), date.substring(0, 4),
						username);
			}
			if (date.length() == 6) {
				list = mService.allTranSerial(username, null, date.substring(4, 6), date.substring(0, 4));
				sum_account = mService.getAccount(null, date.substring(4, 6), date.substring(0, 4), username);
			}
			if (date.length() == 4) {
				list = mService.allTranSerial(username, null, null, date.substring(0, 4));
				sum_account = mService.getAccount(null, null, date.substring(0, 4), username);
			}
		}
		PageInfo<TranSerial> pageList = new PageInfo<TranSerial>(list);
		List<TranSerial> rowsList = pageList.getList();
		int count = (int) pageList.getTotal();
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("date", date);
		model.addAttribute("username", EscapeUtil.unescape(username));
		if (sum_account == null) {
			sum_account = BigDecimal.valueOf(0.0);
		}
		model.addAttribute("sum_account", sum_account);
		return "admin/admin_transeriallist";
	}

	@RequestMapping("/trandetaillist")
	public String trandetaillist(String keywords_id, String date, String username, String currPage,String pageSize, Model model) {

		int curr = 1;
		if (currPage != null) {
			curr = Integer.parseInt(currPage);
			if (curr <= 0) {
				curr = 1;
			}
		}
		if(pageSize!=null){
			PageHelper.startPage(curr, Integer.parseInt(pageSize));
		}else{
			PageHelper.startPage(curr, 10);
		}
		model.addAttribute("pageSize", pageSize);
		List<TranDetail> list = new ArrayList<TranDetail>();
		if (date == null) {
			date = DateUtil.getYesterday();
		}
		if (keywords_id != null) {
			list = mService.tranList(Integer.parseInt(keywords_id), date, username, "1");
		} else {
			list = mService.tranList(0, date, username, "1");
		}
		PageInfo<TranDetail> pageList = new PageInfo<TranDetail>(list);
		List<TranDetail> rowsList = pageList.getList();
		int count = (int) pageList.getTotal();
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("date", date);
		model.addAttribute("username", EscapeUtil.unescape(username));
		BigDecimal sum_account = mService.getAccount(date.substring(6, 8), date.substring(4, 6), date.substring(0, 4),
				username);
		if (sum_account == null) {
			sum_account = BigDecimal.valueOf(0.0);
		}
		model.addAttribute("sum_account", sum_account);
		return "admin/admin_trandetaillist";

	}

	@RequestMapping("/trankeys")
	public String trankeys(String keywords_id, String username, String beginDate, String endDate, String keywords,
			String currPage, String dabiao, String pageSize,Model model) {

		int curr = 1;
		if (currPage != null) {
			curr = Integer.parseInt(currPage);
			if (curr <= 0) {
				curr = 1;
			}
		}
		if(pageSize!=null){
			PageHelper.startPage(curr, Integer.parseInt(pageSize));
		}else{
			PageHelper.startPage(curr, 10);
		}
		model.addAttribute("pageSize", pageSize);
		List<TranDetail> list = new ArrayList<TranDetail>();
		BigDecimal sum_account = null;
		int begin = 0;
		int end = 0;
		if (beginDate != null && !"".equals(beginDate)) {
			begin = Integer.parseInt(beginDate);
		}
		if (endDate != null && !"".equals(endDate)) {
			end = Integer.parseInt(endDate);
		}
		if (keywords_id != null) {
			list = mService.tranKeys(begin, end, username, Integer.parseInt(keywords_id), null, dabiao);
			sum_account = mService.getKeysAccount(begin, end, username, Integer.parseInt(keywords_id), null, dabiao);
		} else {
			if (keywords != null && !"".equals(keywords)) {
				list = mService.tranKeys(begin, end, username, 0, EscapeUtil.unescape(keywords), dabiao);
				sum_account = mService.getKeysAccount(begin, end, username, 0, EscapeUtil.unescape(keywords), dabiao);
			} else {
				list = mService.tranKeys(begin, end, username, 0, null, dabiao);
				sum_account = mService.getKeysAccount(begin, end, username, 0, null, dabiao);
			}
		}
		PageInfo<TranDetail> pageList = new PageInfo<TranDetail>(list);
		List<TranDetail> rowsList = pageList.getList();
		int count = (int) pageList.getTotal();
		List<User> userList = lService.findUserByState("1");
		model.addAttribute("userList", userList);
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("keywords", EscapeUtil.unescape(keywords));
		model.addAttribute("dabiao", dabiao);
		model.addAttribute("username", EscapeUtil.unescape(username));
		if (sum_account == null) {
			sum_account = BigDecimal.valueOf(0.0);
		}
		model.addAttribute("sum_account", sum_account);

		return "admin/admin_trankeys";

	}

	@RequestMapping("/userdetail")
	public String userdetail(String user_id, Model model) {

		if (user_id != null && !"".equals(user_id)) {
			User user = lService.findById(Integer.parseInt(user_id));
			model.addAttribute("u", user);
		}
		return "admin/admin_userdetail";
	}

	@ResponseBody
	@RequestMapping(value = "/userdel", produces = "text/html;charset=UTF-8;")
	public String userdel(String username, HttpSession session) {
		int del = lService.delete(username);
		if (del != 0) {
			List<Keywords> keylist = kService.KeywordsShowByParam(username, null, null, null, null, null,null,null,null,null);
			if (keylist != null) {
				for (Keywords keywords : keylist) {
					kService.delete(keywords.getKeywords_id());
				}
			}
			List<Rechange> relist = mService.rechangeList(username, null, null);
			if (relist != null) {
				for (Rechange rechange : relist) {
					mService.delRechange(username);
				}
			}
			List<TranSerial> serlist = mService.allTranSerial(username, null, null, null);
			if (serlist != null) {
				for (TranSerial tranSerial : serlist) {
					mService.delTranSerial(username);
				}
			}

			List<TranDetail> detlist = mService.tranList(0, null, username, null);
			if (detlist != null) {
				for (TranDetail tranDetail : detlist) {
					mService.delTranDetail(username);
				}
			}
			SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
			log.info(admin.getAdmin_name() + "删除" + username + "所有相关数据成功");
		}
		return "删除成功";
	}

	@ResponseBody
	@RequestMapping(value = "/useropen", produces = "text/html;charset=UTF-8;")
	public String useropen(String username, HttpSession session) {
		String msg = null;
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		int qy = lService.uptState("1", username);
		if (qy != 0) {
			List<Keywords> keylist = kService.KeywordsShowByParam(username, null, null, "0", null, null,null,null,null,null);// 查出管理员所停用的关键字
			if (keylist != null) {
				for (Keywords keywords : keylist) {
					kService.update(keywords.getKeywords_id(), "2");
				}
			}
			log.info(admin.getAdmin_name() + "启用'" + username + "'成功");
			msg = username + "启用成功";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/userstop", produces = "text/html;charset=UTF-8;")
	public String userstop(String username, HttpSession session) {
		String msg = null;
		SeoAdmin admin = (SeoAdmin) session.getAttribute("admin");
		int qy = lService.uptState("0", username);
		if (qy != 0) {
			List<Keywords> keylist = kService.KeywordsShowByParam(username, null, null, "2", null, null,null,null,null,null);// 查出该用户所有启用的关键字
			if (keylist != null) {
				for (Keywords keywords : keylist) {
					kService.update(keywords.getKeywords_id(), "0");// 管理员批量停用时状态
				}
			}
			log.info(admin.getAdmin_name() + "停用'" + username + "'成功");
			msg = username + "停用成功";
		}
		return msg;
	}

	@RequestMapping("/keydr")
	public String keydr(Model model) {
	//	List<User> userList = lService.findUserByState("1");
	//	model.addAttribute("userList", userList);
		return "admin/admin_keydr";
	}

	@RequestMapping("uploadKeyFile")
	public String fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
			String username, Model model) throws IOException {
	//	long startTime = System.currentTimeMillis();
	//	System.out.println("fileName：" + file.getOriginalFilename());
		String fileName = file.getOriginalFilename();
		log.info(fileName);
		String path = PropertiesUtil.getProperty("keyFileUploadPath") + File.separator + fileName;
		File newFile = new File(path);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);

		String message = null;
		try {
			List<List<Map<String, String>>> result = ReadExcel.readExcelWithTitle(path);
			int success = 0;
			int fail = 0;
			int size = 0;
			for (List<Map<String, String>> list : result) {
				for (Map<String, String> map : list) {
					String zsApiUrl = PropertiesUtil.getProperty("zsApiUrl") + "?key="
							+ PropertiesUtil.getProperty("zsApiKey") + "&kws=" + EscapeUtil.escape(map.get("关键字"));
					String zsRes = HttpRequestUtil.sendGet(zsApiUrl);
					JSONObject zsStr = JSONObject.parseObject(zsRes); // 把json字符串转换为json对象
					String zsData = zsStr.getString("data");// 获取json对象的属性值
					int zsStatus = zsStr.getInteger("state");// 状态为1时 获取成功
																// 详情见api文件
					String zsMsg = zsStr.getString("msg");
					ZsModel zs = null;
					Keywords key = new Keywords();
					String map_address=map.get("域名").trim();
					String map_keywords=map.get("关键字").trim();
					String map_engines=map.get("搜索类型").trim();
					if(map.get("域名")==null&&map_keywords==null&&map_engines==null){
						message="请下载文件模版！按照模版录入关键字！";
						return "admin/admin_keydr";
					}
					key.setAdd_date(DateUtil.getToday());
					key.setZsupt_date(DateUtil.getToday());
			//		key.setPmupt_date(DateUtil.getToday());
					key.setLast_date(DateUtil.getToday());
					key.setUsername(username);
					key.setDomain_address(map_address);
					key.setKeywords(map_keywords);
					key.setSearch_engines(map_engines);
					key.setState("1");
					key.setTran_money(BigDecimal.valueOf(0.00));
			//		key.setChupai("9999");
					key.setPage_url("##");
					key.setXinpai("9999");
					key.setZrpai("9999");
					key.setPmbh("--");
					key.setRemarks("");
					key.setKkstyle("1");
					key.setCsdj("0");
					if (zsStatus == 1) {
						zsData = zsData.replace("[", "");
						zsData = zsData.replace("]", "");
						JSONObject zsdata = JSONObject.parseObject(zsData);
						zs = JSONObject.toJavaObject(zsdata, ZsModel.class);
						key.setAllIndex(String.valueOf(zs.getAllindex()));
						key.setMobileIndex(String.valueOf(zs.getMobileindex()));
						key.setPrice(KeyPrice.getPrice(zs.getAllindex(), zs.getMobileindex(), zs.getMobileindex(),
								zs.getKeyword(), map_engines));
						key.setSo360Index(String.valueOf(zs.getSo360index()));
					} else {
						key.setAllIndex("--");
						key.setMobileIndex("--");
						key.setSo360Index("--");
						log.info(map_keywords + "，指数查询接口返回数据情况：" + zsMsg);
						// String err_msg="接口维护中，暂时不能新增关键字，如有疑问请致电客服。";
						// model.addAttribute("err_msg", err_msg);
						// break;
					}
					int save = kService.save(key, map_address, map_keywords,map_engines);
					if (save == 0) {
						fail++;
					} else {
						success++;
					}
				}
				size = size + list.size();
			}
			log.info("新增关键字总数:" + success);
			message = "共导入" + size + "条,导入成功：" + success + "条,失败：" + fail + "条(之前已录入)";
			log.info(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	List<User> userList = lService.findUserByState("1");
	//	model.addAttribute("userList", userList);
		model.addAttribute("msg", message);
	//	long endTime = System.currentTimeMillis();
	//	System.out.println("运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return "admin/admin_keydr";
	}
}
