package com.example.jobms.job.DTO;

import com.example.jobms.job.external.Company;
import com.example.jobms.job.external.Review;

import java.util.List;

public class JobDTO {
    private Long id ;
    private String title ;
    private String description ;
    private String minSalary ;
    private String maxSalary ;
    private String location ;

    private Company company ;
    private List<Review> reviews ;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
