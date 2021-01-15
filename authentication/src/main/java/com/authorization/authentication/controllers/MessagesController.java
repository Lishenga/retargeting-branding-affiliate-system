package com.authorization.authentication.controllers;

import java.util.List;

import com.authorization.authentication.exceptions.messages.MessagesExceptionHandler;
import com.authorization.authentication.models.Messages;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.messages.CreateMessageRequest;
import com.authorization.authentication.requests.messages.DeleteMessageRequest;
import com.authorization.authentication.requests.messages.GetParticularChatMessagesRequest;
import com.authorization.authentication.requests.messages.GetParticularUserMessagesRequest;
import com.authorization.authentication.responses.GeneralResponse;
import com.authorization.authentication.responses.messages.GetAllMessagesResponse;
import com.authorization.authentication.responses.messages.GetParticularChatMessagesResponse;
import com.authorization.authentication.responses.messages.GetParticularUserMessagesResponse;
import com.authorization.authentication.services.MessagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    private GetAllMessagesResponse getAllMessagesResponse = new GetAllMessagesResponse();

    private GeneralResponse generalResponse = new GeneralResponse();

    private GetParticularUserMessagesResponse getParticularUserMessagesResponse = new GetParticularUserMessagesResponse();

    private GetParticularChatMessagesResponse getParticularChatMessagesResponse = new GetParticularChatMessagesResponse();

    private String success = "Success";
    
    @RequestMapping(value = "createmessage", method = RequestMethod.POST)
    public GeneralResponse createMessage(@RequestBody CreateMessageRequest createMessageRequest) throws MessagesExceptionHandler{

        messagesService.createMessage(createMessageRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallmessages", method = RequestMethod.POST)
    public GetAllMessagesResponse getAllMessages(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Messages> messages = messagesService.getAllMessages(generalPagedRequest);
        getAllMessagesResponse.setData(messages);
        getAllMessagesResponse.setMessage(success);
        getAllMessagesResponse.setStatus(200);
        return getAllMessagesResponse;
    }

    @RequestMapping(value = "deletemessage", method = RequestMethod.DELETE)
    public GeneralResponse deleteMessage(@RequestBody DeleteMessageRequest deleteMessageRequest) throws MessagesExceptionHandler {
        messagesService.deleteMessage(deleteMessageRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularusermessages", method = RequestMethod.POST)
    public GetParticularUserMessagesResponse getParticularUserMessages(@RequestBody GetParticularUserMessagesRequest getParticularUserMessagesRequest) throws MessagesExceptionHandler {

        List <Messages> message = messagesService.getParticularUserMessages(getParticularUserMessagesRequest);
        getParticularUserMessagesResponse.setData(message);
        getParticularUserMessagesResponse.setMessage(success);
        getParticularUserMessagesResponse.setStatus(200);
        return getParticularUserMessagesResponse;
    }

    @RequestMapping(value = "getparticularchatmessages", method = RequestMethod.POST)
    public GetParticularChatMessagesResponse getParticularChatMessages(@RequestBody GetParticularChatMessagesRequest getParticularChatMessagesRequest) throws MessagesExceptionHandler {

        List <Messages> message = messagesService.getParticularChatMessages(getParticularChatMessagesRequest);
        getParticularChatMessagesResponse.setData(message);
        getParticularChatMessagesResponse.setMessage(success);
        getParticularChatMessagesResponse.setStatus(200);
        return getParticularChatMessagesResponse;
    }
}