package util;

import com.alibaba.fastjson.JSONArray;
import communication.HttpUtils;
import domain.Applications;
import domain.Stages;
import domain.Jobs;
import praser.Convert;

import java.util.List;

/**
 * @File : SparkInfoUtil.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class SparkInfoUtil {
    public static List<Applications> getApplicationInfo(String key){
        key = key + "?status=running";
        String url = UrlUtil.generateURL(key);
        JSONArray jsonArray = HttpUtils.doGet(url);
        return Convert.toApplicationsList(jsonArray);
    }

    public static List<Jobs> getJobsInfo(String key, String applicationId, Filter filter){
        key = key.replace("{%}", applicationId);
        String url = UrlUtil.generateURL(key);
        JSONArray jsonArray = HttpUtils.doGet(url);
        return Convert.toJobsList(jsonArray, filter);
    }

    public static JSONArray getJobsInfo(String key, String applicationId){
        key = key.replace("{%}", applicationId);
        String url = UrlUtil.generateURL(key);
        return HttpUtils.doGet(url);
    }

    public static List<Stages> getStagesInfo(String key, String applicationId, Filter filter){
        key = key.replace("{%}", applicationId);
        String url = UrlUtil.generateURL(key);
        JSONArray jsonArray = HttpUtils.doGet(url);
        return Convert.toStagesList(jsonArray, filter);
    }

}
