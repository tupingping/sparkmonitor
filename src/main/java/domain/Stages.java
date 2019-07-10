package domain;

import java.io.Serializable;

/**
 * @File : Stages.java
 * @Author: tupingping
 * @Date : 2019/7/3
 * @Desc :
 */
public class Stages implements Serializable {
    private static final long serialVersionUID = 1401665016890714800L;

    private String applicationId;
    private String jobId;
    private String stageId;
    private String status;
    private Long numCompleteTasks;
    private Long numFailedTasks;
    private String completionTime;
    private String submissionTime;

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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNumCompleteTasks() {
        return numCompleteTasks;
    }

    public void setNumCompleteTasks(Long numCompleteTasks) {
        this.numCompleteTasks = numCompleteTasks;
    }

    public Long getNumFailedTasks() {
        return numFailedTasks;
    }

    public void setNumFailedTasks(Long numFailedTasks) {
        this.numFailedTasks = numFailedTasks;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public String getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(String submissionTime) {
        this.submissionTime = submissionTime;
    }
}
