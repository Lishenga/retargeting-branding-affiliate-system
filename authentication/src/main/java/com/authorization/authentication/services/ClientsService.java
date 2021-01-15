package com.authorization.authentication.services;

import java.time.LocalDateTime;
import java.util.Optional;

import com.authorization.authentication.exceptions.clients.ClientsExceptionHandler;
import com.authorization.authentication.models.Clients;
import com.authorization.authentication.repositories.ClientsRepository;
import com.authorization.authentication.requests.clients.CreateClientRequest;
import com.authorization.authentication.requests.clients.UpdateClientRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {
    
    @Autowired
    private ClientsRepository clientsRepository;

    public Clients createClient(CreateClientRequest createClientRequest) throws ClientsExceptionHandler {

        Clients client = new Clients();
        client.createClient(createClientRequest);
        clientsRepository.save(client);
        
        return client;
    }

    public Clients updateClient(UpdateClientRequest updateClientRequest) throws ClientsExceptionHandler {

        Optional <Clients> client = clientsRepository.findById(updateClientRequest.getId());

        if(!client.isPresent()){
            throw new ClientsExceptionHandler("Client not found");
        }

        Clients cli = client.get();

        if(updateClientRequest.getClientId() != null){
            cli.setClientId(updateClientRequest.getClientId());
        }

        if(updateClientRequest.getClientSecret() != null){
            cli.setClientSecret(updateClientRequest.getClientSecret());
        }

        if(updateClientRequest.getNickName() != null){
            cli.setNickName(updateClientRequest.getNickName());
        }

        cli.setUpdatedAt(LocalDateTime.now());
        clientsRepository.save(cli);
        
        return cli;
    }
}