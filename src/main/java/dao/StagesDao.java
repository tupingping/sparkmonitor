package dao;

import domain.Stages;

import java.util.List;

/**
 * @File : StagesDao.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public interface StagesDao {
    Boolean setStagesInfo(Stages stages);
    Boolean setStagesInfoList(List<Stages> stagesList);
    Stages findById(String applicationId, String jobId, String stageId);
}
