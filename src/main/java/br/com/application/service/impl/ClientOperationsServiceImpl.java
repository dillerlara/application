package br.com.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.application.model.client.Client;
import br.com.application.provider.client.ClientProvider;
import br.com.application.service.ClientOperationsService;

@Service
public class ClientOperationsServiceImpl implements ClientOperationsService {

    @Autowired
    private ClientProvider clientProvider;

    @Override
    public List<Client> getAllClients() {

        return this.clientProvider.findAll();
    }

    @Override
    public void delete(Integer clientId) {
        this.clientProvider.delete(clientId);
        
    }

    @Override
    public Client save(Client client) {
        return this.clientProvider.save(client);
    }

    
}
