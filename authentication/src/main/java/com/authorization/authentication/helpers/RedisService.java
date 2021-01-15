package com.authorization.authentication.helpers;

import com.authorization.authentication.exceptions.chat.ChatExceptionHandler;
import com.authorization.authentication.exceptions.messages.MessagesExceptionHandler;
import com.authorization.authentication.requests.chat.CreateChatRequest;
import com.authorization.authentication.requests.messages.CreateMessageRequest;
import com.authorization.authentication.services.ChatService;
import com.authorization.authentication.services.MessagesService;

import org.springframework.beans.factory.annotation.Autowired;

public class RedisService {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private ChatService chatService;

	public void createMessage(CreateMessageRequest createMessageRequest) throws MessagesExceptionHandler {
        messagesService.createMessage(createMessageRequest);
    }

    public void createChat(CreateChatRequest createChatRequest) throws ChatExceptionHandler{
        chatService.createChat(createChatRequest);
    }
}