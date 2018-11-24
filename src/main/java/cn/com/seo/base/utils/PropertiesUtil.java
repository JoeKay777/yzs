package cn.com.seo.base.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static Logger logger=Logger.getLogger(PropertiesUtil.class);
    private static Properties props;
    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        logger.info("��ʼ����properties�ļ�����.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("data.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties�ļ�δ�ҵ�");
        } catch (IOException e) {
            logger.error("����IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("�ļ����رճ����쳣");
            }
        }
        logger.info("����properties�ļ��������...........");
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
