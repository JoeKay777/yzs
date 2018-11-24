package cn.com.seo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.seo.bean.User;
import cn.com.seo.dao.LoginDao;
import cn.com.seo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao ldao;
	
	public List<User> findAll(String register_time, String name,String rechange_status) {
		// TODO Auto-generated method stub
		return ldao.findAll(register_time, name,rechange_status);
	}


	public User findById(int id) {
		// TODO Auto-generated method stub
		return ldao.findById(id);
	}


	public String save(User u) {
		// TODO Auto-generated method stub
		if(ldao.findByName(u.getUsername())==null){
			int s=ldao.save(u);
			if(s==1){
				return u.getUsername()+"注册成功";
			}
		}
		return u.getUsername()+"已被注册";
	}


	public int delete(String username) {
		// TODO Auto-generated method stub
		return ldao.delete(username);
	}


	public boolean update(User u) {
		// TODO Auto-generated method stub

			int up=ldao.update(u);
			if(up==1){
				return true;
			}
		return false;
	}


	public String findByNamePass(String username, String password) {
		// TODO Auto-generated method stub
		if(ldao.findByName(username)!=null){
			User user=ldao.findByNamePass(username, password);
			if(user!=null){
				return "1";
			}else{
				return "密码错误";
			}
		}else{
			return username+"不存在";
		}
	}


	public User findByName(String name) {
		// TODO Auto-generated method stub
		return ldao.findByName(name);
	}


	public List<User> findUserByState(String user_status) {
		// TODO Auto-generated method stub
		return ldao.findUserByState(user_status);
	}

	public int uptState(String user_status, String username) {
		// TODO Auto-generated method stub
		return ldao.uptState(user_status, username);
	}


	public String uptPwd(String oldPwd, String newPwd, int user_id) {
		// TODO Auto-generated method stub
		User user=ldao.findById(user_id);
		if(ldao.findByNamePass(user.getUsername(), oldPwd)!=null){
			int upt=ldao.uptPwd(oldPwd, newPwd, user_id);
			if(upt!=0){
				return "密码修改成功";
			}
		}else{
			return "输入旧密码有误";
		}
		return null;
	}

}
