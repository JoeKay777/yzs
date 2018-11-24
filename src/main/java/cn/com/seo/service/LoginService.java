package cn.com.seo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.User;

public interface LoginService {
	   public List<User> findAll(String status,String name,String rechange_status);
	   public String save(User u);  
	   public int delete(String  username);  
	   public boolean update(User u);  
	   public User findById(int id);  
	   public String findByNamePass(String username,String password);
	   public User findByName(String name);
	   public List<User> findUserByState(String user_status);
	   public int uptState(String user_status,String username);
	   public String uptPwd(String oldPwd,String newPwd,int user_id);
}
