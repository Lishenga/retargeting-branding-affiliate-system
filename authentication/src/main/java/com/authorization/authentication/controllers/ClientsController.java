package com.authorization.authentication.controllers;

import com.authorization.authentication.exceptions.clients.ClientsExceptionHandler;
import com.authorization.authentication.models.Clients;
import com.authorization.authentication.requests.clients.CreateClientRequest;
import com.authorization.authentication.requests.clients.UpdateClientRequest;
import com.authorization.authentication.responses.clients.CreateClientResponse;
import com.authorization.authentication.services.ClientsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    private final CreateClientResponse createClientResponse = new CreateClientResponse();

    @RequestMapping(value = "createclient", method = RequestMethod.POST)
    public CreateClientResponse createClient(@RequestBody final CreateClientRequest createClientRequest) throws ClientsExceptionHandler  {

        final Clients client = clientsService.createClient(createClientRequest);
        createClientResponse.setData(client);
        createClientResponse.setMessage("Success");
        createClientResponse.setStatus(200);
        return createClientResponse;
    }

    @RequestMapping(value = "updateclient", method = RequestMethod.PUT)
    public CreateClientResponse updateClient(@RequestBody final UpdateClientRequest updateClientRequest) throws ClientsExceptionHandler  {

        final Clients client = clientsService.updateClient(updateClientRequest);
        createClientResponse.setData(client);
        createClientResponse.setMessage("Success");
        createClientResponse.setStatus(200);
        return createClientResponse;
    }
}