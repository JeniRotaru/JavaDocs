package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;

/**
 * Created by user on 7/7/2016.
 */
public class Job {

    @Id( name = "job_id")
    private Long jobId;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "min_salary")
    private Long minSalary;

    public Long getMaxSalary() {
        return maxSalary;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Long getJobId() {
        return jobId;
    }

    @Column(name = "max_salary")
    private Long maxSalary;

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!jobId.equals(job.jobId)) return false;
        if (!jobTitle.equals(job.jobTitle)) return false;
        if (!minSalary.equals(job.minSalary)) return false;
        return maxSalary.equals(job.maxSalary);

    }

    @Override
    public int hashCode() {
        int result = jobId.hashCode();
        result = 31 * result + jobTitle.hashCode();
        result = 31 * result + minSalary.hashCode();
        result = 31 * result + maxSalary.hashCode();
        return result;
    }
}
