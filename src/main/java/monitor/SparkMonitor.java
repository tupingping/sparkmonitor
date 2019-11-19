package monitor;

import com.alibaba.fastjson.JSONArray;
import conf.ConfigurationManager;
import constant.Constants;
import dao.ApplicationsDao;
import dao.JobsDao;
import dao.impl.DAOFactory;
import domain.Applications;
import domain.Jobs;
import logger.LogUtil;
import praser.Convert;
import util.DataCompleteUtil;
import util.Filter;
import util.SparkInfoUtil;
import util.TimeFormatUtil;

import java.util.LinkedList;
import java.util.List;



public class SparkMonitor {
    public static void main(String[] args){
        try{
            final String d = TimeFormatUtil.getDateStr(-1);
            LogUtil.getInstance().info("start get applications...");
            List<Applications> applicationsList = SparkInfoUtil.getApplicationInfo(ConfigurationManager.getString(Constants.SPARK_APPLICATIONS));
            ApplicationsDao applicationsDao = DAOFactory.getApplicationsDAO();
            applicationsDao.setApplicationsList(applicationsList);

            List<String> applicationsIdList = new LinkedList<String>();
            for(Applications applications:applicationsList){
                applicationsIdList.add(applications.getApplicationId());
            }

            LogUtil.getInstance().info("start get jobs...");
            for(String id : applicationsIdList){
                JSONArray jobsJsonArray = SparkInfoUtil.getJobsInfo(ConfigurationManager.getString(Constants.SPARK_JOBS), id);

                List<Jobs> jobsList = Convert.toJobsList(jobsJsonArray, new Filter() {
                    @Override
                    public Boolean isValid(Object data) {
                        String submissionTime = (String)data;
                        return submissionTime.compareTo(d) > 0 ? true : false;
                    }
                });

                List<Jobs> jobsCompleteList = DataCompleteUtil.getCompleteJobs(id, jobsList);
                JobsDao jobsDao = DAOFactory.getJobsDAO();
                jobsDao.setJobsInfoList(jobsCompleteList);
            }

        }catch (Exception e){
            LogUtil.getInstance().error("failed");
        }


        LogUtil.getInstance().info("finished");
    }
}
