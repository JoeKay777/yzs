package cn.com.seo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.com.seo.bean.User;

public interface LoginDao {
	   public List<User> findAll(@Param("status")String status,@Param("username")String name,@Param("rechange_status")String rechange_status);
	   public int save(User u);  
	   public int delete(@Param("username")String  username);  
	   public int update(User u);  
	   public User findById(int id);  
	   public User findByNamePass(@Param("username")String username,@Param("password")String password);
	   public User findByName(@Param("username")String name);
	   public List<User> findUserByState(@Param("user_status")String user_status);
	   public int uptState(@Param("user_status")String user_status,@Param("username")String username);
	   public int uptPwd(@Param("oldPwd")String oldPwd,@Param("newPwd")String newPwd,@Param("user_id")int user_id);
}	
