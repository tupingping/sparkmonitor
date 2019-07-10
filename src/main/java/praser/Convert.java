package praser;

import com.alibaba.fastjson.JSONArray;
import domain.Applications;
import domain.Jobs;
import domain.Stages;
import logger.LogUtil;
import util.Filter;
import util.TimeFormatUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @File : Convert.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class Convert {
    public static List<Applications> toApplicationsList(JSONArray data){
        LinkedList<Applications> applicationsLinkedList = new LinkedList<Applications>();
        //TODO:
        for(int i = 0; i < data.size(); i++){
            Applications applications = new Applications();
            String applicationId = data.getJSONObject(i).getString("id");
            String applicationName = data.getJSONObject(i).getString("name");
            JSONArray jsonArray = (JSONArray)data.getJSONObject(i).get("attempts");
            String startTime = jsonArray.getJSONObject(jsonArray.size()-1).getString("startTime");
            startTime = TimeFormatUtil.getFromGmlTime(startTime);
            String endTime = jsonArray.getJSONObject(jsonArray.size()-1).getString("endTime");
            endTime = TimeFormatUtil.getFromGmlTime(endTime);
            String updateTime = jsonArray.getJSONObject(jsonArray.size()-1).getString("lastUpdated");
            updateTime = TimeFormatUtil.getFromGmlTime(updateTime);
            String user = jsonArray.getJSONObject(jsonArray.size()-1).getString("sparkUser");
            Long duryTime = jsonArray.getJSONObject(jsonArray.size()-1).getLong("duration");

            applications.setApplicationId(applicationId);
            applications.setApplicationName(applicationName);
            applications.setStartTime(startTime);
            applications.setEndTime(endTime);
            applications.setUpdateTime(updateTime);
            applications.setUserName(user);
            applications.setDuryTime(duryTime);

            applicationsLinkedList.add(applications);
        }
        return applicationsLinkedList;
    }

    public static List<Jobs> toJobsList(JSONArray data, Filter filter){
        LinkedList<Jobs> jobsLinkedList = new LinkedList<Jobs>();
        //TODO:
        for(int i = 0; i < data.size(); i ++){
            Jobs jobs = new Jobs();
            String jobId = data.getJSONObject(i).getString("jobId");
            String rSql = data.getJSONObject(i).getString("description");
            LogUtil.getInstance().info(rSql);
            String submissionTime = data.getJSONObject(i).getString("submissionTime");
            submissionTime = TimeFormatUtil.getFromGmlTime(submissionTime);
            String completionTime = data.getJSONObject(i).getString("completionTime");
            completionTime = TimeFormatUtil.getFromGmlTime(completionTime);
            Long duryTime = TimeFormatUtil.getTimeDury(submissionTime, completionTime);

            String jobGroup = data.getJSONObject(i).getString("jobGroup");
            String status = data.getJSONObject(i).getString("status");
            Long numTask = data.getJSONObject(i).getLong("numTasks");
            Long numFailedTask = data.getJSONObject(i).getLong("numFailedTasks");
            Long numCompletedStages = data.getJSONObject(i).getLong("numCompletedStages");
            Long numSkippedStages = data.getJSONObject(i).getLong("numSkippedStages");
            Long numFailedStages = data.getJSONObject(i).getLong("numFailedStages");
            if ((!submissionTime.isEmpty()) && filter.isValid(submissionTime)){
                jobs.setJobId(jobId);
                jobs.setSql(rSql);
                jobs.setSubmissionTime(submissionTime);
                jobs.setCompletionTime(completionTime);
                jobs.setDuryTime(duryTime);
                jobs.setJobGroup(jobGroup);
                jobs.setStatus(status);
                jobs.setNumTask(numTask);
                jobs.setNumFailedTask(numFailedTask);
                jobs.setNumCompletedStages(numCompletedStages);
                jobs.setNumSkippedStages(numSkippedStages);
                jobs.setNumFailedStages(numFailedStages);
                jobsLinkedList.add(jobs);
            }
        }
        return jobsLinkedList;
    }

    public static Map<String, List<String>> getMaptoStagesId(JSONArray data, Filter filter){
        HashMap<String, List<String>> stagesIdMap = new HashMap<String, List<String>>();
        //TODO:
        for(int i = 0; i < data.size(); i ++){
            String submissionTime = data.getJSONObject(i).getString("submissionTime");
            submissionTime = TimeFormatUtil.getFromGmlTime(submissionTime);
            if (filter.isValid(submissionTime)){
                String jobId = data.getJSONObject(i).getString("jobId");
                LinkedList<String> stagesIdList = new LinkedList<String>();
                JSONArray jsonArray = data.getJSONObject(i).getJSONArray("stageIds");
                for(int j = 0; j < jsonArray.size(); j++){
                    stagesIdList.add(jsonArray.getString(j));
                }
                stagesIdMap.put(jobId, stagesIdList);
            }
        }
        return stagesIdMap;
    }

    public static List<Stages> toStagesList(JSONArray data, Filter filter){
        LinkedList<Stages> stagesLinkedList = new LinkedList<Stages>();
        //TODO:
        for(int i = 0; i < data.size(); i ++){
            Stages stage = new Stages();
            String stageId = data.getJSONObject(i).getString("stageId");
            String status = data.getJSONObject(i).getString("status");
            Long numCompleteTasks =  data.getJSONObject(i).getLong("numCompleteTasks");
            Long numFailedTasks = data.getJSONObject(i).getLong("numFailedTasks");
            String completionTime = data.getJSONObject(i).getString("completionTime");
            completionTime = TimeFormatUtil.getFromGmlTime(completionTime);
            String submissionTime = data.getJSONObject(i).getString("submissionTime");
            submissionTime = TimeFormatUtil.getFromGmlTime(submissionTime);

            if (filter.isValid(submissionTime)){
                stage.setStageId(stageId);
                stage.setStatus(status);
                stage.setNumCompleteTasks(numCompleteTasks);
                stage.setNumFailedTasks(numFailedTasks);
                stage.setCompletionTime(completionTime);
                stage.setSubmissionTime(submissionTime);
                stagesLinkedList.add(stage);
            }
        }
        return stagesLinkedList;
    }
}
