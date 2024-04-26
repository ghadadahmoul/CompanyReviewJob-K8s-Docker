package com.example.jobms.job.impl;

import com.example.jobms.job.DTO.JobDTO;
import com.example.jobms.job.Job;
import com.example.jobms.job.JobRepository;
import com.example.jobms.job.JobService;
import com.example.jobms.job.clients.CompanyClient;
import com.example.jobms.job.clients.ReviewClient;
import com.example.jobms.job.external.Company;
import com.example.jobms.job.external.Review;
import com.example.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    //private List<Job> jobs =new ArrayList<>();
    private Long nextId=1L ;
    @Autowired
    RestTemplate restTemplate ;
    private CompanyClient companyClient ;
    private ReviewClient reviewClient ;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient,ReviewClient reviewClient) {

        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private JobDTO convertToDto(Job job){
         Company company = companyClient.getCompany(job.getCompanyId());

         List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper
                .mapToJobWithCompanyDto(job,company,reviews);
        //jobDTO.setCompany(company);

        return jobDTO;


    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);

    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true ;

        } catch (Exception e){
            return false ;
        }

    }

    @Override
    public boolean updateJob(Long id, Job upatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()){
                Job job=jobOptional.get();
                job.setTitle(upatedJob.getTitle());
                job.setDescription(upatedJob.getDescription());
                job.setLocation(upatedJob.getLocation());
                job.setMaxSalary(upatedJob.getMaxSalary());
                job.setMinSalary(upatedJob.getMinSalary());
                jobRepository.save(job);
                return true ;
            } else {
        return false;
    }


}}
