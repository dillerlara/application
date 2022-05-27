package br.com.application.service;

import java.util.List;

import br.com.application.model.company.Company;

public interface CompanyService {

    Company save(Company company);

    List<Company> findAll();

    Company findById(Integer id);

    void delete(Integer id);

}
