package dao.impl;

import dao.ApplicationsDao;
import domain.Applications;
import jdbc.JdbcHelper;
import logger.LogUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @File : ApplicationDaoImpl.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class ApplicationDaoImpl implements ApplicationsDao {

    @Override
    public Boolean setApplications(Applications applications) {
        try{
            String sql = "replace into spark_applications(applicationId, applicationName, userName, duryTime, " +
                    "schedulerMode, startTime, updateTime,endTime) "+
                    "values(?,?,?,?,?,?,?,?)";
            Object[] params = new Object[]{
                    applications.getApplicationId(),
                    applications.getApplicationName(),
                    applications.getUserName(),
                    applications.getDuryTime(),
                    applications.getSchedulerMode(),
                    applications.getStartTime(),
                    applications.getUpdateTime(),
                    applications.getEndTime()
            };

            JdbcHelper jdbcHelper = JdbcHelper.getInstance();
            jdbcHelper.executeUpdate(sql, params);
        }catch (Exception e){
            LogUtil.getInstance().error("setApplications failed.", e);
            return false;
        }

        return true;
    }

    @Override
    public Boolean setApplicationsList(List<Applications> applicationsList) {
        try{
            String sql = "replace into spark_applications(applicationId, applicationName, userName, duryTime, " +
                    "schedulerMode, startTime, updateTime,endTime) "+
                    "values(?,?,?,?,?,?,?,?)";

            List<Object[]> paramsList = new LinkedList<Object[]>();
            for(int i = 0; i < applicationsList.size(); i++){
                Object[] params = new Object[]{
                        applicationsList.get(i).getApplicationId(),
                        applicationsList.get(i).getApplicationName(),
                        applicationsList.get(i).getUserName(),
                        applicationsList.get(i).getDuryTime(),
                        applicationsList.get(i).getSchedulerMode(),
                        applicationsList.get(i).getStartTime(),
                        applicationsList.get(i).getUpdateTime(),
                        applicationsList.get(i).getEndTime()
                };
                paramsList.add(params);
            }

            JdbcHelper jdbcHelper = JdbcHelper.getInstance();
            jdbcHelper.executeUpdateBatch(sql, paramsList);
        }catch (Exception e){
            LogUtil.getInstance().error("setApplicationsList failed.", e);
            return false;
        }

        return true;
    }

    @Override
    public Applications findById(String applicationId) {
        final Applications applications = new Applications();
        String sql = "select applicationId, applicationName, userName, duryTime, schedulerMode, startTime, updateTime," +
                " endTime from spark_applications where applicationId = ?";
        Object[] params = new Object[]{applicationId};

        JdbcHelper jdbcHelper = JdbcHelper.getInstance();
        jdbcHelper.executeQuery(sql, params, new JdbcHelper.QueryCallback(){
            @Override
            public void process(ResultSet rs) throws Exception {
                if (rs.next()){
                    String applicationId = rs.getString(0);
                    String applicationName = rs.getString(1);
                    String userName = rs.getString(2);
                    Long duryTime = rs.getLong(3);
                    String schedulerMode = rs.getString(4);
                    String startTime = rs.getString(5);
                    String updateTime = rs.getString(6);
                    String endTime = rs.getString(7);

                    applications.setApplicationId(applicationId);
                    applications.setApplicationName(applicationName);
                    applications.setUserName(userName);
                    applications.setDuryTime(duryTime);
                    applications.setSchedulerMode(schedulerMode);
                    applications.setStartTime(startTime);
                    applications.setUpdateTime(updateTime);
                    applications.setEndTime(endTime);
                }
            }
        });

        return applications;
    }
}
