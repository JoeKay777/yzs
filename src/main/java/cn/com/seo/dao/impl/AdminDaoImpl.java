package cn.com.seo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.seo.bean.SeoAdmin;
import cn.com.seo.dao.AdminDao;

public class AdminDaoImpl implements AdminDao{

	@Autowired
	private AdminDao dao;
	public int insertAdmin(SeoAdmin admin) {
		// TODO Auto-generated method stub
		return dao.insertAdmin(admin);
	}

	public int updateAdmin(SeoAdmin admin) {
		// TODO Auto-generated method stub
		return dao.updateAdmin(admin);
	}

	public int delAdmin(int admin_id) {
		// TODO Auto-generated method stub
		return dao.delAdmin(admin_id);
	}

	public SeoAdmin selByName(String admin_name) {
		// TODO Auto-generated method stub
		return dao.selByName(admin_name);
	}

	public SeoAdmin selByNamePass(String admin_name, String admin_pass) {
		// TODO Auto-generated method stub
		return dao.selByNamePass(admin_name, admin_pass);
	}

	public int uptPwd(String oldPwd, String newPwd, int admin_id) {
		// TODO Auto-generated method stub
		return dao.uptPwd(oldPwd, newPwd, admin_id);
	}

	public List<SeoAdmin> selAdminList(String admin_grade) {
		// TODO Auto-generated method stub
		return dao.selAdminList(admin_grade);
	}

	public SeoAdmin selById(int admin_id) {
		// TODO Auto-generated method stub
		return dao.selById(admin_id);
	}

}
