package util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @File : TimeFormatUtil.java
 * @Author: tupingping
 * @Date : 2019/7/5
 * @Desc :
 */
public class TimeFormatUtil {

    public static String getFromGmlTime(String gmlTime){
        try{
            String[] strList = gmlTime.split("T");
            String t = strList[0] + " " + strList[1].substring(0, 8);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = f.parse(t);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            rightNow.add(Calendar.HOUR_OF_DAY,8);
            return f.format(rightNow.getTime());

        }catch (Exception e){

        }
        return "";
    }

    public static String getDateStr(int index){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, index);
        Date d = cal.getTime();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(d);
    }

    public static Long getTimeDury(String before, String after){
        try{
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long t1 = f.parse(after).getTime();
            Long t2 = f.parse(before).getTime();
            return (t1 - t2)/1000;
        }catch (Exception e){

        }
        return 0L;
    }
}
