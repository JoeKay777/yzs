package cn.com.seo.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.UserKeyDabiao;
import cn.com.seo.dao.KeywordsDao;

public class KeywordsDaoImpl implements KeywordsDao{
	@Autowired
	private KeywordsDao dao;
	public List<Keywords> KeywordsShowByParam(String username, String keywords, String search_engines,String state,
			String pxStyle,String pmbh,String chupai,String xinpai,String pmstate,String csdj) {
		// TODO Auto-generated method stub
		return dao.KeywordsShowByParam(username, keywords, search_engines,state,pxStyle,pmbh,chupai,xinpai,pmstate,csdj);
	}

	public int save(Keywords words) {
		// TODO Auto-generated method stub
		return dao.save(words);
	}

	public int delete(int keywords_id) {
		// TODO Auto-generated method stub
		return dao.delete(keywords_id);
	}

	public int update(int keywords_id,String state) {
		// TODO Auto-generated method stub
		return dao.update(keywords_id,state);
	}

	public Keywords KeywordsBySerKey(String keywords, String search_engines,String domain_address) {
		// TODO Auto-generated method stub
		return dao.KeywordsBySerKey(keywords, search_engines,domain_address);
	}


	public int keywordsCount(String date, String keywords, String address) {
		// TODO Auto-generated method stub
		return dao.keywordsCount(date, keywords, address);
	}

	public Keywords keywordsById(int keywords_id) {
		// TODO Auto-generated method stub
		return dao.keywordsById(keywords_id);
	}



	public int zsUpdate(Keywords words) {
		// TODO Auto-generated method stub
		return dao.zsUpdate(words);
	}

	public int pmUpdate(Keywords words) {
		// TODO Auto-generated method stub
		return dao.pmUpdate(words);
	}

	public int uptParam(Keywords words) {
		// TODO Auto-generated method stub
		return dao.uptParam(words);
	}

	public int uptKeyword(Keywords words) {
		// TODO Auto-generated method stub
		return dao.uptKeyword(words);
	}

	public int userKeyDbSave(UserKeyDabiao ukdb) {
		// TODO Auto-generated method stub
		return dao.userKeyDbSave(ukdb);
	}

	public UserKeyDabiao findDabiaoByDate(String date,String username) {
		// TODO Auto-generated method stub
		return dao.findDabiaoByDate(date,username);
	}

	
}
