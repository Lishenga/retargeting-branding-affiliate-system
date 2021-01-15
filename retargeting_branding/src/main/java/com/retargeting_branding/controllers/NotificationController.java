package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Notification;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.notification.CreateNotificationRequest;
import com.retargeting_branding.requests.notification.GetParticularNotificationRequest;
import com.retargeting_branding.requests.notification.UpdateNotificationRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.notification.GeneralNotificationResponse;
import com.retargeting_branding.responses.notification.GetParticularNotificationResponse;
import com.retargeting_branding.services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {
    
    private String success = "Success";

    @Autowired
    private NotificationService notificationService;

    private GeneralResponse generalResponse = new GeneralResponse();

    private GeneralNotificationResponse generalNotificationResponse = new GeneralNotificationResponse();

    private GetParticularNotificationResponse getParticularNotificationResponse = new GetParticularNotificationResponse();

    @RequestMapping(value = "createnotification", method = RequestMethod.POST)
    public GeneralResponse createNotification(@RequestBody CreateNotificationRequest createNotificationRequest) throws GeneralExceptionHandler{

        notificationService.createNotification(createNotificationRequest);
        generalResponse.setMessage(this.success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallnotifications", method = RequestMethod.POST)
    public GeneralNotificationResponse getAllNotifications(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Notification> not = notificationService.getAllNotifications(generalPagedRequest);
        generalNotificationResponse.setData(not);
        generalNotificationResponse.setMessage(success);
        generalNotificationResponse.setStatus(200);
        return generalNotificationResponse;
    }

    @RequestMapping(value = "deletenotification", method = RequestMethod.DELETE)
    public GeneralResponse deleteNotification(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        notificationService.deleteNotification(generalDeleteRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularnotification", method = RequestMethod.POST)
    public GetParticularNotificationResponse getParticularNotification(@RequestBody GetParticularNotificationRequest getParticularNotificationRequest) throws GeneralExceptionHandler {

        Notification not = notificationService.getParticularNotification(getParticularNotificationRequest);
        getParticularNotificationResponse.setData(not);
        getParticularNotificationResponse.setMessage(success);
        getParticularNotificationResponse.setStatus(200);
        return getParticularNotificationResponse;
    }

    @RequestMapping(value = "updatenotification", method = RequestMethod.PUT)
    public GetParticularNotificationResponse updateNotification(@RequestBody UpdateNotificationRequest updateNotificationRequest) throws GeneralExceptionHandler {

        Notification not = notificationService.updateNotification(updateNotificationRequest);
        getParticularNotificationResponse.setData(not);
        getParticularNotificationResponse.setMessage(success);
        getParticularNotificationResponse.setStatus(200);
        return getParticularNotificationResponse;
    }
}