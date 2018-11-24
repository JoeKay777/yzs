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
		map.put("username", new StringBuffer("闫军军\r\n"));
		map.put("keyStr", new StringBuffer("hahahahaha\r\n"));
	}
	
	
 
	/**
	 * 写文件
	 * 
	 * @param newStr
	 *            新内容
	 * @throws IOException
	 */
	public static boolean writeTxtFile(StringBuffer str,String filenameTemp) throws IOException {
		// 先读取原有文件内容，然后进行写入操作
		boolean flag = false;
		File filename = new File(filenameTemp);
		if (!filename.exists()) {
			filename.createNewFile();
		}
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			// 文件路径
			File file = new File(filenameTemp);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(str.toString().trim());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			// TODO 自动生成 catch 块
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
