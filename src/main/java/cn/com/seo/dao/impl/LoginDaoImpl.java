package cn.com.seo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.seo.bean.User;
import cn.com.seo.dao.LoginDao;

public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private LoginDao logindao;
	
	public List<User> findAll(String register_time, String name,String rechange_status) {
		// TODO Auto-generated method stub
		return logindao.findAll(register_time, name,rechange_status);
	}

	public int save(User u) {
		// TODO Auto-generated method stub
		return logindao.save(u);
	}


	public int update(User u) {
		// TODO Auto-generated method stub
		return logindao.update(u);
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return logindao.findById(id);
	}

	public User findByNamePass(String username, String password) {
		// TODO Auto-generated method stub
		return logindao.findByNamePass(username, password);
	}

	public User findByName(String name) {
		// TODO Auto-generated method stub
		return logindao.findByName(name);
	}

	public List<User> findUserByState(String user_status) {
		// TODO Auto-generated method stub
		return logindao.findUserByState(user_status);
	}

	public int uptState(String user_status,String username) {
		// TODO Auto-generated method stub
		return logindao.uptState(user_status,username);
	}

	public int delete(String username) {
		// TODO Auto-generated method stub
		return logindao.delete(username);
	}

	public int uptPwd(String oldPwd, String newPwd, int user_id) {
		// TODO Auto-generated method stub
		return logindao.uptPwd(oldPwd, newPwd, user_id);
	}

}
