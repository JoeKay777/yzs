package cn.com.seo.control;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.seo.base.utils.DateUtil;
import cn.com.seo.base.utils.EscapeUtil;
import cn.com.seo.base.utils.JspView;
import cn.com.seo.bean.SeoAdmin;
import cn.com.seo.bean.User;
import cn.com.seo.bean.UserKeyDabiao;
import cn.com.seo.service.AdminService;
import cn.com.seo.service.KeywordsService;
import cn.com.seo.service.LoginService;
import cn.com.seo.service.MoneyService;

@Controller
@RequestMapping("/user")
public class LoginAction {
	
	public Logger log=Logger.getLogger(LoginAction.class);
	@Autowired
	private LoginService loginService;
	@Autowired
	private KeywordsService kService;
	@Autowired
	private MoneyService mService;
	@Autowired
	private AdminService aService;
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
	String date = df.format(new Date());
	
	@ResponseBody
	@RequestMapping(value="/login",produces="text/html;charset=UTF-8;")
	public String login(String username,String password,HttpSession session){
		User user=(User) loginService.findByName(username);
		if(user!=null){
			session.setAttribute("user", user);
		}
		String msg=loginService.findByNamePass(username, password);
		if("1".equals(msg)){
			log.info(user.getUsername()+"已登录");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value="/register",produces="text/html;charset=UTF-8;")
	public String register(User user){
		user.setUser_status("1");
		user.setRole_type("0");
		user.setRegister_time(date);
		user.setBalance(BigDecimal.valueOf(0.00));
		user.setUlevel("1");//普通会员
		user.setKeyamount("--");
		user.setRechange_status("0");
		user.setUser_msg("欢迎来到排名系统，有问题请联系客服~");
		String msg=loginService.save(user);
		return msg;
	}
	
	@RequestMapping("/userListShow")
	public String userListShow(String currPage,String user_status,String rechange_status,String username,String pageSize,Model model){
		int curr=1;
		if(currPage!=null){
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
		List<User> userAll=loginService.findAll(user_status, username,rechange_status);
		PageInfo<User> userPageList=new PageInfo<User>(userAll);
		List<User> rows=userPageList.getList();//获取每页数据
		int count=(int) userPageList.getTotal();//获取总条数
		model.addAttribute("count", count);
		model.addAttribute("rows", rows);
		model.addAttribute("currPage", curr);
		model.addAttribute("user_status", user_status);
		model.addAttribute("username",  EscapeUtil.unescape(username));
		
		return "admin/admin_userlist";
	}
	
	@RequestMapping("/userDetail")
	public String userDetail(String user_id,Model model){
		if(user_id!=null&&!"".equals(user_id)){
			User user=loginService.findById(Integer.parseInt(user_id));
			model.addAttribute("u", user);
		}
		return JspView.USERDETAIL;
	}
	
	@RequestMapping("/userUpdate")
	public String userUpdate(String user_id,String txtTruename,String txtEmail,String txtMobile,String txtQQ,Model model){
		
		User u=loginService.findById(Integer.parseInt(user_id));
		u.setCompany(txtTruename);
		u.setMail(txtEmail);
		u.setPhone(txtMobile);
		u.setQq(txtQQ);
		u.setUser_id(Integer.parseInt(user_id));
		try {
			boolean b=loginService.update(u);
			if(b){
				model.addAttribute("msg", "修改成功");
			}else{
				model.addAttribute("msg", "修改失败，请联系客服");
			}
			if(user_id!=null&&!"".equals(user_id)){
				User user=loginService.findById(Integer.parseInt(user_id));
				model.addAttribute("u", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return JspView.USERDETAIL;
	}
	
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session){
		
		User user=(User)session.getAttribute("user");
		if(user!=null){
			log.info(user.getUsername()+"退出登录，清除session");
			session.invalidate();
		}
		return JspView.LOGIN;
	}
	
	@ResponseBody
	@RequestMapping(value="/uptpwd",produces="text/html;charset=UTF-8;")
	public String uptpwd(String oldPwd,String newPwd,HttpSession session){
		User user=(User)session.getAttribute("user");
		String msg=loginService.uptPwd(oldPwd, newPwd, user.getUser_id());
		return msg;
	}
	
	@RequestMapping("/index")
	public String index(Model model,HttpSession session){
		User user=(User)session.getAttribute("user");
		String yesterday=DateUtil.getYesterday();
		BigDecimal userBalance=loginService.findById(user.getUser_id()).getBalance();
		if(userBalance==null){
			userBalance=BigDecimal.valueOf(0.00);
		}
		int dabiaoCount=mService.tranList(0,yesterday, user.getUsername(), "1").size();
		BigDecimal yesterdayAccount=mService.getAccount(yesterday.substring(6, 8), yesterday.substring(4,6 ),yesterday.substring(0, 4),user.getUsername());
		if(yesterdayAccount==null){
			yesterdayAccount=BigDecimal.valueOf(0.00);
		}
		int keyCount=kService.KeywordsShowByParam(user.getUsername(), null, null, null, null, null,null,null,null,null).size();
		int checkCount=kService.KeywordsShowByParam(user.getUsername(), null, null, "1", null, null,null,null,null,null).size();
		BigDecimal yjxf=mService.introdayDeductAccount(user.getUsername(), "2", "1");
		if(yjxf==null){
			yjxf=BigDecimal.valueOf(0.00);
		}
		model.addAttribute("yjxf", yjxf);
		int a=userBalance.compareTo(yjxf);
		if(a!=1){
			model.addAttribute("tsmsg", "您的余额不足以今日消费，请及时充值~");
		}
		String[] str=new String[10];
		String[] dabiaoArray=new String[10];
		for(int i=0;i<10;i++){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -(i+1));
			String d = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
			UserKeyDabiao ukdb=kService.findDabiaoByDate(d,user.getUsername());
			if(ukdb!=null){
				dabiaoArray[9-i]=ukdb.getUdb_dbnum();
			}else{
				dabiaoArray[9-i]="0";
			}
			str[9-i]=d;
		}
		model.addAttribute("dateArray", Arrays.toString(str));
		model.addAttribute("dabiaoArray", Arrays.toString(dabiaoArray));
		
		model.addAttribute("userBalance", userBalance);
		model.addAttribute("dabiaoCount", dabiaoCount);
		model.addAttribute("yesterdayAccount", yesterdayAccount);
		model.addAttribute("keyCount", keyCount);
		model.addAttribute("checkCount", checkCount);
		User u=loginService.findById(user.getUser_id());
		model.addAttribute("u", u);
		List<SeoAdmin> adminList=aService.selAdminList("1");
		SeoAdmin admin=adminList.get(0);
		model.addAttribute("admin", admin);
		return "client/index";
	}
	
	@RequestMapping("/keyAdd")
	public String keyadd(Model model,String user_id){
		User u=loginService.findById(Integer.parseInt(user_id));
		model.addAttribute("u", u);
		return "client/keywords-add";
	}
	
	@RequestMapping("/keyDr")
	public String keydr(Model model,String user_id){
		User u=loginService.findById(Integer.parseInt(user_id));
		model.addAttribute("u", u);
		return "client/keywords-dr";
	}
	
	@RequestMapping("/keyPrice")
	public String keyprice(Model model,String user_id){
		User u=loginService.findById(Integer.parseInt(user_id));
		model.addAttribute("u", u);
		return "client/keywords-price";
	}
	
	@RequestMapping("/rechangeAdd")
	public String rechangeAdd(Model model,String user_id){
		User u=loginService.findById(Integer.parseInt(user_id));
		model.addAttribute("u", u);
		List<SeoAdmin> adminList=aService.selAdminList(null);
		model.addAttribute("adminList", adminList);
		return "client/rechange-add";
	}
	
	@RequestMapping("/userUptPwd")
	public String userUptPwd(Model model,String user_id){
		User u=loginService.findById(Integer.parseInt(user_id));
		model.addAttribute("u", u);
		return "client/uptpwd";
	}
	
	@ResponseBody
	@RequestMapping("/getUsername")
	public Object getUsername(String username){
		List<User> userList=loginService.findAll(null, username, null);
		return userList;
	}
	
}
