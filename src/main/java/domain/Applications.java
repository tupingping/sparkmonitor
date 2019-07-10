package domain;

import java.io.Serializable;

/**
 * @File : Applications.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class Applications implements Serializable {
    private static final long serialVersionUID = 7820678964800593786L;

    private String applicationId;
    private String applicationName;
    private String userName;
    private Long duryTime;
    private String schedulerMode;
    private String startTime;
    private String updateTime;
    private String endTime;

    public String getApplicationId() {
        return applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getUserName() {
        return userName;
    }

    public Long getDuryTime() {
        return duryTime;
    }

    public String getSchedulerMode() {
        return schedulerMode;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDuryTime(Long duryTime) {
        this.duryTime = duryTime;
    }

    public void setSchedulerMode(String schedulerMode) {
        this.schedulerMode = schedulerMode;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
