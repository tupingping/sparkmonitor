package dao.impl;

import dao.ApplicationsDao;
import dao.JobsDao;
import dao.StagesDao;

/**
 * @File : DAOFactory.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class DAOFactory {
    public static ApplicationsDao getApplicationsDAO(){
        return new ApplicationDaoImpl();
    }

    public static JobsDao getJobsDAO(){
        return new JobsDaoImpl();
    }

    public static StagesDao getStagesDAO(){
        return new StagesDaoImpl();
    }
}
