package ro.teamnet.zth.appl.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestObject;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobServiceImpl;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    private final JobServiceImpl jobs = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return jobs.findAllJobs();
    }

    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name="id") String id) {
        return jobs.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public void deleteJob(@MyRequestParam(name="id") String id) {
        jobs.deleteOneJob(id);
    }

    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public Job saveJob(@MyRequestObject Job job) {
        return jobs.saveOneJob(job);
    }


}
