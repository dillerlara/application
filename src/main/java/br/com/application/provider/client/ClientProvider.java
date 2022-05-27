package br.com.application.provider.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.application.model.client.Client;

@Repository
public interface ClientProvider extends JpaRepository<Client,Integer> {

        
}
