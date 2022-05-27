package br.com.application.service;

import java.util.List;

import br.com.application.model.client.Client;

public interface ClientOperationsService {
    
    List <Client> getAllClients();

    Client save(Client client);

    void  delete (Integer clientId);

    
}
