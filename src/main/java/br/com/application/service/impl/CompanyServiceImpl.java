package br.com.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.application.model.company.Company;
import br.com.application.provider.company.CompanyProvider;
import br.com.application.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyProvider companyProvider;

    @Override
    public void delete(Integer id) {
        companyProvider.delete(id);        
    }

    @Override
    public List<Company> findAll() {
        return companyProvider.findAll();
    }

    @Override
    public Company findById(Integer id) {
        return companyProvider.findOne(id);
    }

    @Override
    public Company save(Company company) {
        return companyProvider.save(company);
    }
    
    
}
