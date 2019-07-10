package dao.impl;

import dao.JobsDao;
import domain.Jobs;
import jdbc.JdbcHelper;
import logger.LogUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @File : JobsDaoImpl.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class JobsDaoImpl implements JobsDao {
    @Override
    public Boolean setJobsInfo(Jobs jobs) {
        try{
            String sql = "replace into spark_jobs(applicationId, jobId, rSql, submissionTime, completionTime, duryTime, jobGroup, status, numTask, " +
                    "numFailedTask, numCompletedStages, numSkippedStages, numFailedStages) " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            Object[] params = new Object[]{
                    jobs.getApplicationId(),
                    jobs.getJobId(),
                    jobs.getSql(),
                    jobs.getSubmissionTime(),
                    jobs.getCompletionTime(),
                    jobs.getDuryTime(),
                    jobs.getJobGroup(),
                    jobs.getStatus(),
                    jobs.getNumTask(),
                    jobs.getNumFailedTask(),
                    jobs.getNumCompletedStages(),
                    jobs.getNumSkippedStages(),
                    jobs.getNumFailedStages()
            };

            JdbcHelper jdbcHelper = JdbcHelper.getInstance();
            jdbcHelper.executeUpdate(sql, params);
        }catch (Exception e){
            LogUtil.getInstance().error("setJobInfo failed.", e);
            return false;
        }

        return true;
    }

    @Override
    public Boolean setJobsInfoList(List<Jobs> jobsList) {
        try{
            String sql = "replace into spark_jobs(applicationId, jobId, rSql, submissionTime, completionTime, duryTime, jobGroup, status, numTask, " +
                    "numFailedTask, numCompletedStages, numSkippedStages, numFailedStages) " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            List<Object[]> paramsList = new LinkedList<Object[]>();
            for(int i = 0; i < jobsList.size(); i++){
                Object[] params = new Object[]{
                        jobsList.get(i).getApplicationId(),
                        jobsList.get(i).getJobId(),
                        jobsList.get(i).getSql(),
                        jobsList.get(i).getSubmissionTime(),
                        jobsList.get(i).getCompletionTime(),
                        jobsList.get(i).getDuryTime(),
                        jobsList.get(i).getJobGroup(),
                        jobsList.get(i).getStatus(),
                        jobsList.get(i).getNumTask(),
                        jobsList.get(i).getNumFailedTask(),
                        jobsList.get(i).getNumCompletedStages(),
                        jobsList.get(i).getNumSkippedStages(),
                        jobsList.get(i).getNumFailedStages()
                };
                paramsList.add(params);
            }

            JdbcHelper jdbcHelper = JdbcHelper.getInstance();
            jdbcHelper.executeUpdateBatch(sql, paramsList);
        }catch (Exception e){
            LogUtil.getInstance().error("setJobsInfoList failed.", e);
            return false;
        }

        return true;
    }

    @Override
    public Jobs findById(String applicationsId, String jobId) {
        return null;
    }
}
