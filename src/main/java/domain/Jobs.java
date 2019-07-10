package domain;

import java.io.Serializable;

/**
 * @File : Jobs.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class Jobs implements Serializable {
    private static final long serialVersionUID = -3928241880652702896L;

    private String applicationId;
    private String jobId;
    private String rSql;
    private String submissionTime;
    private String completionTime;
    private Long duryTime;
    private String jobGroup;
    private String status;
    private Long numTask;
    private Long numFailedTask;
    private Long numCompletedStages;
    private Long numSkippedStages;
    private Long numFailedStages;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSql() {
        return rSql;
    }

    public void setSql(String sql) {
        this.rSql = sql;
    }

    public String getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(String submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public Long getDuryTime() {
        return duryTime;
    }

    public void setDuryTime(Long duryTime) {
        this.duryTime = duryTime;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNumTask() {
        return numTask;
    }

    public void setNumTask(Long numTask) {
        this.numTask = numTask;
    }

    public Long getNumFailedTask() {
        return numFailedTask;
    }

    public void setNumFailedTask(Long numFailedTask) {
        this.numFailedTask = numFailedTask;
    }

    public Long getNumCompletedStages() {
        return numCompletedStages;
    }

    public void setNumCompletedStages(Long numCompletedStages) {
        this.numCompletedStages = numCompletedStages;
    }

    public Long getNumSkippedStages() {
        return numSkippedStages;
    }

    public void setNumSkippedStages(Long numSkippedStages) {
        this.numSkippedStages = numSkippedStages;
    }

    public Long getNumFailedStages() {
        return numFailedStages;
    }

    public void setNumFailedStages(Long numFailedStages) {
        this.numFailedStages = numFailedStages;
    }
}
