package br.com.application.provider.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.application.model.company.Company;

@Repository
public interface CompanyProvider extends JpaRepository<Company, Integer> {
    
}
