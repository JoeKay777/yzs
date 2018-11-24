package cn.com.seo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.SeoAdmin;

public interface AdminDao {
	public int insertAdmin(SeoAdmin admin);
	public int updateAdmin(SeoAdmin admin);
	public int delAdmin(int admin_id);
	public SeoAdmin selByName(String admin_name);
	public SeoAdmin selById(int admin_id);
	public SeoAdmin selByNamePass(@Param("admin_name") String admin_name,@Param("admin_pass")String admin_pass);
	public int uptPwd(@Param("oldPwd")String oldPwd,@Param("newPwd")String newPwd,@Param("admin_id")int admin_id);
	public List<SeoAdmin> selAdminList(@Param("admin_grade")String admin_grade);
}
