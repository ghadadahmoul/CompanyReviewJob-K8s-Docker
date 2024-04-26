package com.example.companyms.company.Impl;

import com.example.companyms.company.Company;
import com.example.companyms.company.CompanyRepository;
import com.example.companyms.company.CompanyService;
import com.example.companyms.company.clients.ReviewClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository ;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    @Override
    public boolean updateCompany(Long id,Company updatedCompany) {
        Optional <Company> companyOptional= companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(updatedCompany.getDescription());
            companyToUpdate.setName(updatedCompany.getName());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false ;


        }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }



    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        } else {
        return false ; }
    }



}



