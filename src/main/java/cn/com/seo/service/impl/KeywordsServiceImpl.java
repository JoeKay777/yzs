package cn.com.seo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.seo.bean.Keywords;
import cn.com.seo.bean.UserKeyDabiao;
import cn.com.seo.dao.KeywordsDao;
import cn.com.seo.service.KeywordsService;

@Service
public class KeywordsServiceImpl implements KeywordsService{

	@Autowired
	private KeywordsDao kdao;
	public List<Keywords> KeywordsShowByParam(String username, String keywords, String search_engines,String state,
			String pxStyle,String pmbh,String chupai,String xinpai,String pmstate,String csdj) {
		// TODO Auto-generated method stub
		return kdao.KeywordsShowByParam(username, keywords, search_engines,state,pxStyle,pmbh,chupai,xinpai,pmstate,csdj);
	}


	public int delete(int keywords_id) {
		// TODO Auto-generated method stub
		return kdao.delete(keywords_id);
	}

	public int update(int keywords_id,String state) {
		// TODO Auto-generated method stub
		return kdao.update(keywords_id,state);
	}

	public Keywords KeywordsBySerKey(String keywords, String search_engines,String domain_address) {
		// TODO Auto-generated method stub
		return kdao.KeywordsBySerKey(keywords, search_engines,domain_address);
	}



	public int save(Keywords words,  String address, String keywords,String engines) {
		// TODO Auto-generated method stub
		int s=0;
		if(KeywordsBySerKey(keywords, engines, address)==null){
			s=kdao.save(words);
		}
		return s;
	}

	public int keywordsCount(String date, String keywords, String address) {
		// TODO Auto-generated method stub
		return kdao.keywordsCount(date, keywords, address);
	}


	public Keywords keywordsById(int keywords_id) {
		// TODO Auto-generated method stub
		return kdao.keywordsById(keywords_id);
	}






	public int zsUpdate(Keywords words) {
		// TODO Auto-generated method stub
		return kdao.zsUpdate(words);
	}


	public int pmUpdate(Keywords words) {
		// TODO Auto-generated method stub
		return kdao.pmUpdate(words);
	}


	public int uptParam(Keywords words) {
		// TODO Auto-generated method stub
		return kdao.uptParam(words);
	}


	public int uptKeyword(Keywords words) {
		// TODO Auto-generated method stub
		return kdao.uptKeyword(words);
	}


	public int userKeyDbSave(UserKeyDabiao ukdb) {
		// TODO Auto-generated method stub
		return kdao.userKeyDbSave(ukdb);
	}


	public UserKeyDabiao findDabiaoByDate(String date,String username) {
		// TODO Auto-generated method stub
		return kdao.findDabiaoByDate(date,username);
	}




}
