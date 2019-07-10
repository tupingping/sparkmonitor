package dao.impl;

import dao.StagesDao;
import domain.Stages;
import jdbc.JdbcHelper;
import logger.LogUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @File : StagesDaoImpl.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class StagesDaoImpl implements StagesDao {
    @Override
    public Boolean setStagesInfo(Stages stages) {
        try{
            String sql = "replace into spark_stages(applicationId, jobId, stageId, status, numCompleteTasks, numFailedTasks, " +
                    "completionTime, submissionTime) values(?,?,?,?,?,?,?,?)";

            Object[] params = new Object[]{
                    stages.getApplicationId(),
                    stages.getJobId(),
                    stages.getStageId(),
                    stages.getStatus(),
                    stages.getNumCompleteTasks(),
                    stages.getNumFailedTasks(),
                    stages.getCompletionTime(),
                    stages.getSubmissionTime()
            };

            JdbcHelper jdbcHelper = JdbcHelper.getInstance();
            jdbcHelper.executeUpdate(sql, params);
        }catch (Exception e){
            LogUtil.getInstance().error("setStagesInfo failed.", e);
            return false;
        }

        return true;
    }

    @Override
    public Boolean setStagesInfoList(List<Stages> stagesList) {
        try{
            String sql = "replace into spark_stages(applicationId, jobId, stageId, status, numCompleteTasks, numFailedTasks, " +
                    "completionTime, submissionTime) values(?,?,?,?,?,?,?,?)";

            List<Object[]> paramsList = new LinkedList<Object[]>();
            for(int i = 0; i < stagesList.size(); i++){
                Object[] params = new Object[]{
                        stagesList.get(i).getApplicationId(),
                        stagesList.get(i).getJobId(),
                        stagesList.get(i).getStageId(),
                        stagesList.get(i).getStatus(),
                        stagesList.get(i).getNumCompleteTasks(),
                        stagesList.get(i).getNumFailedTasks(),
                        stagesList.get(i).getCompletionTime(),
                        stagesList.get(i).getSubmissionTime()
                };
                paramsList.add(params);
            }

            JdbcHelper jdbcHelper = JdbcHelper.getInstance();
            jdbcHelper.executeUpdateBatch(sql, paramsList);
        }catch (Exception e){
            LogUtil.getInstance().error("setStagesInfoList failed.", e);
            return false;
        }

        return true;
    }

    @Override
    public Stages findById(String applicationId, String jobId, String stageId) {
        return null;
    }
}
