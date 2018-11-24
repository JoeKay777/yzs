package cn.com.seo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.SeoAdmin;

public interface AdminService {
	public String insertAdmin(SeoAdmin admin);
	public String updateAdmin(SeoAdmin admin);
	public String delAdmin(int admin_id);
	public SeoAdmin selByName(String admin_name);
	public SeoAdmin selById(int admin_id);
	public String selByNamePass(String admin_name,String admin_pass);
	public String uptPwd(String oldPwd,String newPwd,int admin_id);
	public List<SeoAdmin> selAdminList(String admin_grade);
}
