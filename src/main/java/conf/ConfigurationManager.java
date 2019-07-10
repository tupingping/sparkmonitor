package conf;

import logger.LogUtil;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {

    private static Properties prop = new Properties();

    static {
        try{
            InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream("monitor.properties");
            prop.load(inputStream);
        }catch (Exception e){
            LogUtil.getInstance().error("load log configuration exception.", e);
        }
    }

    public static String getString(String key){
        return prop.getProperty(key);
    }

    public static Integer getInteger(String key){
        String value = prop.getProperty(key);
        try {
            return Integer.valueOf(value);
        }catch (Exception e){
            LogUtil.getInstance().error("fail to get Integer.", e);
        }
        return 0;
    }

    public static Boolean getBoolean(String key){
        String value = prop.getProperty(key);
        try {
            return Boolean.valueOf(value);
        }catch (Exception e){
            LogUtil.getInstance().error("fail to get Boolean.", e);
        }
        return false;
    }

}
