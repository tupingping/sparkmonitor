package util;

import domain.Jobs;
import domain.Stages;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @File : DataCompleteUtil.java
 * @Author: tupingping
 * @Date : 2019/7/5
 * @Desc :
 */
public class DataCompleteUtil {
    public static List<Jobs> getCompleteJobs(String applicationId, List<Jobs> jobs){
        for(Jobs j : jobs){
            j.setApplicationId(applicationId);
        }
        return jobs;
    }

    public static List<Stages> getCompleteStages(String applicationId, Map<String, List<String>> stageMap, List<Stages> stages){
        for(Stages s : stages){
            s.setApplicationId(applicationId);
            String stageId = s.getStageId();
            for(Map.Entry<String, List<String>> entry : stageMap.entrySet()){
                if(entry.getValue().contains(stageId)){
                    s.setJobId(entry.getKey());
                    break;
                }
            }
        }

        return stages;
    }

}
