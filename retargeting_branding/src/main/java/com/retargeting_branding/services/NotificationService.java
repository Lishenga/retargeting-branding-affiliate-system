package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Notification;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.notification.CreateNotificationRequest;
import com.retargeting_branding.requests.notification.GetParticularNotificationRequest;
import com.retargeting_branding.requests.notification.UpdateNotificationRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends CommonMethods{
    
    public Notification createNotification(CreateNotificationRequest createNotificationRequest) throws GeneralExceptionHandler {

        Optional <Users> user = usersRepository.findById(createNotificationRequest.getUserId());

        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Notification not = new Notification();
        not.createNotification(createNotificationRequest);
        not.setUserId(user.get());
        not.setCreatedBy(user.get());
        not.setUpdatedBy(user.get());
        notificationRepository.save(not);
        
        return not;
    }

    public List <Notification> getAllNotifications(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Notification> notification = notificationRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return notification.getContent();
    }

    public void deleteNotification(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {

        Optional <Notification> find = notificationRepository.findById(generalDeleteRequest.getId());

        if(!find.isPresent()){
            throw new GeneralExceptionHandler(this.notificationError);
        }

        Notification not = find.get();
        not.setIsDeleted(true);
        not.setDeletedAt(LocalDateTime.now());
        notificationRepository.save(not);
    }

    public Notification getParticularNotification(GetParticularNotificationRequest getParticularNotificationRequest) throws GeneralExceptionHandler {

        Optional <Notification> not = notificationRepository.findById(getParticularNotificationRequest.getNotificationId());

        if(!not.isPresent()){
            throw new GeneralExceptionHandler(this.notificationError);
        }

        return not.get();
    }

    public Notification updateNotification(UpdateNotificationRequest updateNotificationRequest) throws GeneralExceptionHandler {

        Optional <Notification> not = notificationRepository.findById(updateNotificationRequest.getNotificationId());

        if(!not.isPresent()){
            throw new GeneralExceptionHandler(this.notificationError);
        }

        Notification noti = not.get();

        if(updateNotificationRequest.getMessage() != null){
            noti.setMessage(updateNotificationRequest.getMessage());
        }

        if(updateNotificationRequest.getUrl() != null){
            noti.setUrl(updateNotificationRequest.getUrl());
        }

        if(updateNotificationRequest.getUserId() != null){

            Optional <Users> user = usersRepository.findById(updateNotificationRequest.getUserId());

            if(!user.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }

            noti.setUserId(user.get());
        }

        if(updateNotificationRequest.getUpdatedBy() != null){

            Optional <Users> user = usersRepository.findById(updateNotificationRequest.getUpdatedBy());

            if(!user.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }

            noti.setUserId(user.get());
        }

        notificationRepository.save(noti);

        return noti;
    }
}