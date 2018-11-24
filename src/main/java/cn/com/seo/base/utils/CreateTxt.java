package cn.com.seo.base.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
 
public class CreateTxt {
 
	public static void main(String[] args) throws IOException {
		Map<String, StringBuffer> map=new HashMap<String, StringBuffer>();
		map.put("username", new StringBuffer("�ƾ���\r\n"));
		map.put("keyStr", new StringBuffer("hahahahaha\r\n"));
	}
	
	
 
	/**
	 * д�ļ�
	 * 
	 * @param newStr
	 *            ������
	 * @throws IOException
	 */
	public static boolean writeTxtFile(StringBuffer str,String filenameTemp) throws IOException {
		// �ȶ�ȡԭ���ļ����ݣ�Ȼ�����д�����
		boolean flag = false;
		File filename = new File(filenameTemp);
		if (!filename.exists()) {
			filename.createNewFile();
		}
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			// �ļ�·��
			File file = new File(filenameTemp);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(str.toString().trim());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			// TODO �Զ����� catch ��
			throw e1;
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return flag;
	}
 
}
