package com.authorization.authentication.controllers;

import java.util.List;

import com.authorization.authentication.exceptions.accesslogs.AccessLogsExceptionHandler;
import com.authorization.authentication.models.AccessLogs;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.accesslogs.DeleteAccessLogRequest;
import com.authorization.authentication.requests.accesslogs.GetParticularAccessLogRequest;
import com.authorization.authentication.responses.GeneralResponse;
import com.authorization.authentication.responses.accesslogs.GeneralAccessLogsResponse;
import com.authorization.authentication.responses.accesslogs.GeneralPagedAccessLogsResponse;
import com.authorization.authentication.services.AccessLogsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accesslogs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccessLogsController {

    @Autowired
    private AccessLogsService accessLogsService;

    private GeneralAccessLogsResponse generalAccessLogsResponse = new GeneralAccessLogsResponse();
    
    private GeneralResponse generalResponse = new GeneralResponse(); 

    private GeneralPagedAccessLogsResponse generalPagedAccessLogsResponse = new GeneralPagedAccessLogsResponse(); 

    private String success = "Success";

    @RequestMapping(value = "getallaccesslogs", method = RequestMethod.POST)
    public GeneralPagedAccessLogsResponse getAllAccessLogs(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <AccessLogs> access = accessLogsService.getAllAccessLogs(generalPagedRequest);
        generalPagedAccessLogsResponse.setData(access);
        generalPagedAccessLogsResponse.setMessage(this.success);
        generalPagedAccessLogsResponse.setStatus(200);
        return generalPagedAccessLogsResponse;
    }

    @RequestMapping(value = "deleteaccesslog", method = RequestMethod.DELETE)
    public GeneralResponse deleteAccessLog(@RequestBody DeleteAccessLogRequest deleteAccessLogRequest) throws AccessLogsExceptionHandler {
        accessLogsService.deleteAccessLog(deleteAccessLogRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularaccesslog", method = RequestMethod.POST)
    public GeneralAccessLogsResponse getParticularAccessLog(@RequestBody GetParticularAccessLogRequest getParticularAccessLogRequest) throws AccessLogsExceptionHandler {

        AccessLogs access = accessLogsService.getParticularAccessLog(getParticularAccessLogRequest);
        generalAccessLogsResponse.setData(access);
        generalAccessLogsResponse.setMessage(success);
        generalAccessLogsResponse.setStatus(200);
        return generalAccessLogsResponse;
    }
}