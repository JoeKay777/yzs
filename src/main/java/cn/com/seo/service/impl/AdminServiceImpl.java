package cn.com.seo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.seo.bean.SeoAdmin;
import cn.com.seo.dao.AdminDao;
import cn.com.seo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adao;
	public String insertAdmin(SeoAdmin admin) {
		// TODO Auto-generated method stub
		SeoAdmin seoadmin=this.selByName(admin.getAdmin_name());
		if(seoadmin==null){
			int insert=adao.insertAdmin(admin);
			if(insert!=0){
				return admin.getAdmin_name()+"��ӳɹ�";//��ӳɹ�
			}
		}else{
			return admin.getAdmin_name()+"�û����Ѵ���";
		}
		return "���ʧ��";
	}

	public String updateAdmin(SeoAdmin admin) {
		// TODO Auto-generated method stub
		int update = adao.updateAdmin(admin);
		if(update!=0){
			return "���ĳɹ�";
		}
		return "����ʧ��";
	}

	public String delAdmin(int admin_id) {
		// TODO Auto-generated method stub
		int del=adao.delAdmin(admin_id);
		if(del!=0){
			return "ɾ���ɹ�";
		}
		return "ɾ��ʧ��";
	}

	public SeoAdmin selByName(String admin_name) {
		// TODO Auto-generated method stub
		return adao.selByName(admin_name);
	}

	public String selByNamePass(String admin_name, String admin_pass) {
		// TODO Auto-generated method stub
		SeoAdmin seo=this.selByName(admin_name);
		if(seo!=null){
			SeoAdmin s=adao.selByNamePass(admin_name, admin_pass);
			if(s!=null){
				return "1";//��¼�ɹ�
			}else{
				return "���벻��ȷ";
			}
		}else{
			return admin_name+"�û���������";
		}
	}

	public String uptPwd(String oldPwd, String newPwd, int admin_id) {
		// TODO Auto-generated method stub
		SeoAdmin admin=selById(admin_id);
		if(adao.selByNamePass(admin.getAdmin_name(), oldPwd)!=null){
			int upt=adao.uptPwd(oldPwd, newPwd, admin_id);
			if(upt!=0){
				return "�����޸ĳɹ�";
			}
		}else{
			return "�������������";
		}
		return null;
	}

	public List<SeoAdmin> selAdminList(String admin_grade) {
		// TODO Auto-generated method stub
		return adao.selAdminList(admin_grade);
	}

	public SeoAdmin selById(int admin_id) {
		// TODO Auto-generated method stub
		return adao.selById(admin_id);
	}

}
