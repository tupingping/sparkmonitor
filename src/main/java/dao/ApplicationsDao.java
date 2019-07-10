package dao;

import domain.Applications;

import java.util.List;

/**
 * @File : ApplicationsDao.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public interface ApplicationsDao {
    Boolean setApplications(Applications applications);
    Boolean setApplicationsList(List<Applications> applicationsList);
    Applications findById(String applicationId);
}
