package util;

import conf.ConfigurationManager;
import constant.Constants;

/**
 * @File : UrlUtil.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class UrlUtil {
    public static String generateURL(String key){
        return  "http://" + ConfigurationManager.getString(Constants.SPARKSQL_SERVER_IP) + ":" +
                ConfigurationManager.getString(Constants.SPARKSQL_SERVER_PORT) + key;
    }
}
