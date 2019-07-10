package dao;

import domain.Jobs;

import java.util.List;

/**
 * @File : JobsDao.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public interface JobsDao {
    Boolean setJobsInfo(Jobs jobs);
    Boolean setJobsInfoList(List<Jobs> jobsList);
    Jobs findById(String applicationsId, String jobId);
}
