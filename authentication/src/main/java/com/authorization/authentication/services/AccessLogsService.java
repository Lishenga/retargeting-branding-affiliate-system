package com.authorization.authentication.services;

import java.util.List;
import java.util.Optional;

import com.authorization.authentication.exceptions.accesslogs.AccessLogsExceptionHandler;
import com.authorization.authentication.exceptions.security.SecurityExceptionHandler;
import com.authorization.authentication.models.AccessLogs;
import com.authorization.authentication.models.Clients;
import com.authorization.authentication.models.Token;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.AccessLogsRepository;
import com.authorization.authentication.repositories.ClientsRepository;
import com.authorization.authentication.repositories.TokenRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.accesslogs.CreateAccessLogsRequest;
import com.authorization.authentication.requests.accesslogs.DeleteAccessLogRequest;
import com.authorization.authentication.requests.accesslogs.GetParticularAccessLogRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccessLogsService {
    
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private String error = "AccessLog not found";


    public void saveAccessLogs(CreateAccessLogsRequest createAccessLogsRequest) throws SecurityExceptionHandler {

        AccessLogs accessLogs = new AccessLogs();
        accessLogs.saveAccessLogs(createAccessLogsRequest);

        if(createAccessLogsRequest.getUser() != null){
            Optional <Users> user = usersRepository.findById(createAccessLogsRequest.getUser());
            if(!user.isPresent()){
                throw new SecurityExceptionHandler("User not found");
            }
            accessLogs.setUser(user.get());
            accessLogs.setCreatedBy(user.get());
            accessLogs.setUpdatedBy(user.get());
        }

        if(createAccessLogsRequest.getClient() != null){
            Optional <Clients> client = clientsRepository.findById(createAccessLogsRequest.getClient());
            if(!client.isPresent()){
                throw new SecurityExceptionHandler("Client not found");
            }
            accessLogs.setClient(client.get());
        }

        if(createAccessLogsRequest.getToken() != null){
            Optional <Token> token = tokenRepository.findById(createAccessLogsRequest.getToken());
            if(!token.isPresent()){
                throw new SecurityExceptionHandler("Token not found");
            }
            accessLogs.setAccessToken(token.get());
        }

        accessLogsRepository.save(accessLogs);
    }

    public List <AccessLogs> getAllAccessLogs(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <AccessLogs> access = accessLogsRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return access.getContent();
    }

    public void deleteAccessLog(DeleteAccessLogRequest deleteAccessLogRequest) throws AccessLogsExceptionHandler {

        Optional <AccessLogs> find = accessLogsRepository.findById(deleteAccessLogRequest.getAccessLogId());

        if(!find.isPresent()){
            throw new AccessLogsExceptionHandler(this.error);
        }

        AccessLogs access = find.get();
        access.setIsDeleted(true);
        accessLogsRepository.save(access);
    }

    public AccessLogs getParticularAccessLog(GetParticularAccessLogRequest getParticularAccessLogRequest) throws AccessLogsExceptionHandler {

        Optional <AccessLogs> access = accessLogsRepository.findById(getParticularAccessLogRequest.getAccessLogId());

        if(!access.isPresent()){
            throw new AccessLogsExceptionHandler(this.error);
        }

        return access.get();
    }
}