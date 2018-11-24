package cn.com.seo.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.UserKeyDabiao;

public interface KeywordsService {
	public List<Keywords> KeywordsShowByParam(String username,String keywords,String search_engines,
			String state,String pxStyle,String pmbh,String chupai,String xinpai,String pmstate,String csdj);//客户端关键字查询
	public int save(Keywords words,String address,String keywords,String engines);
	public int delete(int keywords_id);
	public int update(int keywords_id,String state);
	public Keywords KeywordsBySerKey(String keywords, String search_engines,String domain_address);
	public int zsUpdate(Keywords words);
	
	public int keywordsCount( String date, String keywords,String address);
	
	public Keywords keywordsById(int keywords_id);
	public int pmUpdate(Keywords words);
	public int uptParam(Keywords words);
	public int uptKeyword(Keywords words);
	public int userKeyDbSave(UserKeyDabiao ukdb);
	public UserKeyDabiao findDabiaoByDate(String date,String username);
}
