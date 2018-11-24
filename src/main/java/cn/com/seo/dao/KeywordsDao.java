package cn.com.seo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.UserKeyDabiao;

public interface KeywordsDao {
	public List<Keywords> KeywordsShowByParam(@Param("username") String username,@Param("keywords") String keywords
			,@Param("search_engines") String search_engines,@Param("state") String state,
			@Param("pxStyle")String pxStyle,@Param("pmbh")String pmbh,
			@Param("chupai")String chupai,@Param("xinpai")String xinpai,@Param("pmstate")String pmstate,@Param("csdj")String csdj);//客户端关键字查询
	public int save(Keywords words);
	public int delete(int keywords_id);
	public int update(@Param("keywords_id") int keywords_id,@Param("state") String state);
	public int keywordsCount(@Param("date") String date,@Param("keywords") String keywords
			,@Param("address") String address);
	public Keywords KeywordsBySerKey(@Param("keywords") String keywords
			,@Param("search_engines") String search_engines,@Param("domain_address")String domain_address);
	public int zsUpdate(Keywords words);
	
	public Keywords keywordsById(int keywords_id);
	
	public int pmUpdate(Keywords words);
	
	public int uptParam(Keywords words);
	
	public int uptKeyword(Keywords words);
	
	public int userKeyDbSave(UserKeyDabiao ukdb);
	public UserKeyDabiao findDabiaoByDate(@Param("date")String date,@Param("username")String username);
	
}
