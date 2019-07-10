package logger;

import org.apache.log4j.Logger;

/**
 * @File : LogUtil.java
 * @Author: tupingping
 * @Date : 2019/7/1
 * @Desc : package logger
 */
public class LogUtil {
    private static LogUtil instance = null;
    private static Logger logger = null;

    static {
        logger = Logger.getLogger(LogUtil.class);
    }

    private LogUtil(){
    }

    public static LogUtil getInstance(){
        synchronized (LogUtil.class){
            if(instance == null){
                instance = new LogUtil();
            }
        }
        return instance;
    }

    public  void debug(String str){
        logger.debug(str);
    }

    public  void debug(String str, Exception e){
        logger.debug(str, e);
    }

    public  void info(String str){
        logger.info(str);
    }

    public  void info(String str, Exception e){
        logger.info(str, e);
    }

    public  void warn(String str){
        logger.warn(str);
    }

    public  void warn(String str, Exception e){
        logger.warn(str, e);
    }

    public  void error(String str){
        logger.error(str);
    }

    public  void error(String str, Exception e){
        logger.error(str, e);
    }

    public  void fatal(String str){
        logger.fatal(str);
    }

    public  void fatal(String str, Exception e){
        logger.fatal(str, e);
    }

}
