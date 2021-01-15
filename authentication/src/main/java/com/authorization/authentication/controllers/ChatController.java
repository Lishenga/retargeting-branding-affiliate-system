package com.authorization.authentication.controllers;

import java.util.List;

import com.authorization.authentication.exceptions.chat.ChatExceptionHandler;
import com.authorization.authentication.models.Chat;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.chat.CreateChatRequest;
import com.authorization.authentication.requests.chat.DeleteChatRequest;
import com.authorization.authentication.requests.chat.GetParticularChatRequest;
import com.authorization.authentication.requests.chat.GetParticularUserChatsRequest;
import com.authorization.authentication.responses.GeneralResponse;
import com.authorization.authentication.responses.chat.GeneralChatResponse;
import com.authorization.authentication.responses.chat.GeneralPagedChatsResponse;
import com.authorization.authentication.services.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    private GeneralChatResponse generalChatResponse = new GeneralChatResponse();

    private GeneralPagedChatsResponse generalPagedChatsResponse = new GeneralPagedChatsResponse();

    private GeneralResponse generalResponse = new GeneralResponse();

    private String success = "Success";

    @RequestMapping(value = "createchat", method = RequestMethod.POST)
    public GeneralResponse createChat(@RequestBody CreateChatRequest createChatRequest) throws ChatExceptionHandler {

        chatService.createChat(createChatRequest);
        generalResponse.setMessage(this.success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallchats", method = RequestMethod.POST)
    public GeneralPagedChatsResponse getAllChats(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Chat> chats = chatService.getAllChats(generalPagedRequest);
        generalPagedChatsResponse.setData(chats);
        generalPagedChatsResponse.setMessage(success);
        generalPagedChatsResponse.setStatus(200);
        return generalPagedChatsResponse;
    }

    @RequestMapping(value = "deletechat", method = RequestMethod.DELETE)
    public GeneralResponse deleteChat(@RequestBody DeleteChatRequest deleteChatRequest) throws ChatExceptionHandler {
        chatService.deleteChat(deleteChatRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularchat", method = RequestMethod.POST)
    public GeneralChatResponse getParticularRole(@RequestBody GetParticularChatRequest getParticularChatRequest) throws ChatExceptionHandler {

        Chat chat = chatService.getParticularChat(getParticularChatRequest);
        generalChatResponse.setData(chat);
        generalChatResponse.setMessage(this.success);
        generalChatResponse.setStatus(200);
        return generalChatResponse;
    }

    @RequestMapping(value = "getparticularuserchats", method = RequestMethod.POST)
    public GeneralPagedChatsResponse getParticularUserChats(@RequestBody GetParticularUserChatsRequest getParticularUserChatsRequest) throws ChatExceptionHandler {

        List <Chat> chats = chatService.getParticularUserChats(getParticularUserChatsRequest);
        generalPagedChatsResponse.setData(chats);
        generalPagedChatsResponse.setMessage(success);
        generalPagedChatsResponse.setStatus(200);
        return generalPagedChatsResponse;
    }
}