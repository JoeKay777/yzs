package cn.com.seo.control;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.seo.base.utils.DateUtil;
import cn.com.seo.base.utils.EscapeUtil;
import cn.com.seo.base.utils.JspView;
import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.Rechange;
import cn.com.seo.bean.SeoAdmin;
import cn.com.seo.bean.TranDetail;
import cn.com.seo.bean.TranSerial;
import cn.com.seo.bean.User;
import cn.com.seo.service.LoginService;
import cn.com.seo.service.MoneyService;

@Controller
@RequestMapping("/money")
public class MoneyAction {
	public Logger log=Logger.getLogger(MoneyAction.class);
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
	String date = df.format(new Date());
	@Autowired
	private MoneyService mService;
	@Autowired
	private LoginService lService;
	
	
	@ResponseBody
	@RequestMapping("/saveRechange")
	public String saveRechange(String amount,String payment_style,String add_time,String describe,String username,HttpSession session){
			SeoAdmin admin=(SeoAdmin) session.getAttribute("admin");
			Rechange re=new Rechange();
			re.setAdd_time(add_time);
			re.setAmount(BigDecimal.valueOf(Integer.parseInt(amount)));
			re.setRe_describe(describe);
			re.setPayment_style(payment_style);
			re.setUsername(username);
			int save=mService.saveCheck(re);
			String msg=null;
			if(save!=0){
				User user=lService.findByName(username);
				BigDecimal balance=user.getBalance();
				BigDecimal amount1=BigDecimal.valueOf(Integer.parseInt(amount));
				if(balance==null){
					balance=BigDecimal.valueOf(0.00);
				}
				BigDecimal trueBalance=balance.add(amount1);
				int a=trueBalance.compareTo(BigDecimal.valueOf(0.00));
				if(a==1){
					mService.updateBalance(trueBalance,"1", username);
				}else{
					mService.updateBalance(trueBalance,"2", username);
				}
				msg="1";
				log.info("操作人:"+admin.getAdmin_name()+"||添加成功："+add_time+username+"充值"+amount+"人民币---"+describe);
			}else{
				msg="0";
			}
		return msg;
	}
	
	@RequestMapping("/rechangeList")
	public String rechangeList(String date,String currPage,String pageSize,Model model,HttpSession session){
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
		User user=(User) session.getAttribute("user");
		if("".equals(date)){
			date=null;
		}
		List<Rechange> list=mService.rechangeList(user.getUsername(), date,null);
		PageInfo<Rechange> pageList=new PageInfo<Rechange>(list);
		List<Rechange> rowsList=pageList.getList();
		int count=(int) pageList.getTotal();
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		User u=lService.findById(user.getUser_id());
		model.addAttribute("u", u);
		return JspView.RECHANGELIST;
	}
	
	@RequestMapping("/tranSerialList")
	public String tranSerialList(String date ,String currPage,Model model,HttpSession session){
		int curr=1;
		if(currPage!=null){
			curr=Integer.parseInt(currPage);
			if(curr<=0){
				curr=1;
			}
		}
		PageHelper.startPage(curr, 10);
		User user=(User) session.getAttribute("user");
		String username=user.getUsername();
		BigDecimal sum_account=null;
		List<TranSerial> list=new ArrayList<TranSerial>();
		if(date==null||"".equals(date)){
			sum_account=mService.getAccount(null, null,null,username);
			list=mService.allTranSerial(username, null, null,null);
		}else{
			if(date.length()==8){
				sum_account=mService.getAccount(date.substring(6, 8), date.substring(4,6 ),date.substring(0, 4),username);
				list=mService.allTranSerial(username, date.substring(6, 8), date.substring(4,6 ),date.substring(0, 4));
			}
			if(date.length()==6){
				sum_account=mService.getAccount(null, date.substring(4,6 ),date.substring(0, 4),username);
				list=mService.allTranSerial(username,null, date.substring(4,6 ),date.substring(0, 4));
			}
			if(date.length()==4){
				sum_account=mService.getAccount(null, null,date.substring(0, 4),username);
				list=mService.allTranSerial(username, null, null,date.substring(0, 4));
			}
		}
		PageInfo<TranSerial> pageList=new PageInfo<TranSerial>(list);
		List<TranSerial> rowsList=pageList.getList();
		int count=(int) pageList.getTotal();
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("date", date);
		if(sum_account==null){
			sum_account=BigDecimal.valueOf(0.0);
		}
		model.addAttribute("sum_account", sum_account);
		User u=lService.findById(user.getUser_id());
		model.addAttribute("u", u);
		return JspView.TRANLIST;
	}
	
	@RequestMapping("/tranDetailList")
	public String tranDetailList(String keywords_id,String date,String currPage,String dabiao,String pageSize,Model model,HttpSession session){
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
		if(date==null){
			date=DateUtil.getYesterday();
		}
		User user=(User)session.getAttribute("user");
		List<TranDetail> list=new ArrayList<TranDetail>();
		if(keywords_id!=null){
			list=mService.tranList(Integer.parseInt(keywords_id), date,user.getUsername(),"1");
		}else{
			list=mService.tranList(0, date,user.getUsername(),"1");
		}
		PageInfo<TranDetail> pageList=new PageInfo<TranDetail>(list);
		List<TranDetail> rowsList=pageList.getList();
		int count=(int) pageList.getTotal();
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("date", date);
		BigDecimal sum_account=mService.getAccount(date.substring(6, 8), date.substring(4,6 ),date.substring(0, 4),user.getUsername());
		if(sum_account==null){
			sum_account=BigDecimal.valueOf(0.0);
		}
		model.addAttribute("sum_account", sum_account);
		User u=lService.findById(user.getUser_id());
		model.addAttribute("u", u);
		return JspView.TRANDETAIL;
	}
	
	@RequestMapping("/tranKeys")
	public String tranKeys(String keywords_id,String beginDate,String endDate,String pageSize,String keywords,String currPage,String dabiao,Model model,HttpSession session){
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
		User user=(User)session.getAttribute("user");
		String username=user.getUsername();
		List<TranDetail> list=new ArrayList<TranDetail>();
		int begin=0;
		int end=0;
		if(beginDate!=null&&!"".equals(beginDate)){
			begin=Integer.parseInt(beginDate);
		}
		if(endDate!=null&&!"".equals(endDate)){
			end=Integer.parseInt(endDate);
		}
		BigDecimal sum_account=null;
		if(keywords_id!=null){
			list=mService.tranKeys(begin, end, username, Integer.parseInt(keywords_id), null, dabiao);
			sum_account=mService.getKeysAccount(begin, end, username, Integer.parseInt(keywords_id), null, dabiao);
		}else{
			if(keywords!=null&&!"".equals(keywords)){
				list=mService.tranKeys(begin, end, username, 0, EscapeUtil.unescape(keywords), dabiao);
				sum_account=mService.getKeysAccount(begin, end, username, 0, EscapeUtil.unescape(keywords), dabiao);
			}else{
				list=mService.tranKeys(begin, end, username, 0, null, dabiao);
				sum_account=mService.getKeysAccount(begin, end, username, 0, null, dabiao);
			}
		}
		PageInfo<TranDetail> pageList=new PageInfo<TranDetail>(list);
		List<TranDetail> rowsList=pageList.getList();
		int count=(int) pageList.getTotal();
		model.addAttribute("rows", rowsList);
		model.addAttribute("count", count);
		model.addAttribute("currPage", curr);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("keywords", EscapeUtil.unescape(keywords));
		model.addAttribute("dabiao", dabiao);
		if(sum_account==null){
			sum_account=BigDecimal.valueOf(0.0);
		}
		model.addAttribute("sum_account", sum_account);
		User u=lService.findById(user.getUser_id());
		model.addAttribute("u", u);
		return "client/tran-key";
	}
	
}
