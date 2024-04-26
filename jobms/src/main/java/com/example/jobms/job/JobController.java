package com.example.jobms.job;

import com.example.jobms.job.DTO.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;
    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){

        return ResponseEntity.ok(jobService.findAll());
    }

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){

        jobService.createJob(job);
        return new ResponseEntity<>("success !!",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDTO = jobService.getJobById(id);
        if (jobDTO !=null)
            return new ResponseEntity<>(jobDTO,HttpStatus.OK) ;
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job upatedJob){
        boolean updated = jobService.updateJob(id,upatedJob);
        if (updated)
            return new ResponseEntity<>("Job Updated",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }
}
