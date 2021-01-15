package com.authorization.authentication.services;

import java.util.List;
import java.util.Optional;

import com.authorization.authentication.exceptions.messages.MessagesExceptionHandler;
import com.authorization.authentication.models.Chat;
import com.authorization.authentication.models.Messages;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.ChatRepository;
import com.authorization.authentication.repositories.MessagesRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.messages.CreateMessageRequest;
import com.authorization.authentication.requests.messages.DeleteMessageRequest;
import com.authorization.authentication.requests.messages.GetParticularChatMessagesRequest;
import com.authorization.authentication.requests.messages.GetParticularUserMessagesRequest;
import com.authorization.authentication.responses.messages.CreateMessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {

    @Autowired
	private RedisTemplate<String, CreateMessageResponse> redisCreateMessagesResponse;

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsersRepository usersRepository;

    private String redisCreateMessage = "createMessageResponse";

    private String chatError = "Chat does not exist";

    private String senderError = "Sender does not exist";

    private String receiverError = "Receiver does not exist";

    private CreateMessageResponse createMessageResponse = new CreateMessageResponse();
    
    public void createMessage(CreateMessageRequest createMessageRequest) throws MessagesExceptionHandler {

        Optional <Chat> chat = chatRepository.findById(createMessageRequest.getChatId());

        Optional <Users> senderUser = usersRepository.findById(createMessageRequest.getSender());

        Optional <Users> receiverUser = usersRepository.findById(createMessageRequest.getReceiver());

        if(!chat.isPresent()){
            createMessageResponse.setData(null);
            createMessageResponse.setStatus(500);
            createMessageResponse.setMessage(chatError);
            redisCreateMessagesResponse.convertAndSend(redisCreateMessage, createMessageResponse);

            throw new MessagesExceptionHandler(chatError); 
        }

        if(!senderUser.isPresent()){
            createMessageResponse.setData(null);
            createMessageResponse.setStatus(500);
            createMessageResponse.setMessage(senderError);
            redisCreateMessagesResponse.convertAndSend(redisCreateMessage, createMessageResponse);

            throw new MessagesExceptionHandler(senderError); 
        }

        if(!receiverUser.isPresent()){
            createMessageResponse.setData(null);
            createMessageResponse.setStatus(500);
            createMessageResponse.setMessage(receiverError);
            redisCreateMessagesResponse.convertAndSend(redisCreateMessage, createMessageResponse);

            throw new MessagesExceptionHandler(receiverError); 
        }

        Messages message = new Messages();
        message.createMessage(createMessageRequest);
        message.setChat(chat.get());
        message.setSender(senderUser.get());
        message.setReceiver(receiverUser.get());
        message.setCreatedBy(senderUser.get());
        message.setUpdatedBy(senderUser.get());

        messagesRepository.save(message);

        createMessageResponse.setData(message);
        createMessageResponse.setStatus(200);
        createMessageResponse.setMessage("Success");
        redisCreateMessagesResponse.convertAndSend(redisCreateMessage, createMessageResponse);
    }

    public List <Messages> getAllMessages(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Messages> message = messagesRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return message.getContent();
    }

    public void deleteMessage(DeleteMessageRequest deleteMessageRequest) throws MessagesExceptionHandler {

        Optional <Messages> mess = messagesRepository.findById(deleteMessageRequest.getMessageId());

        if(!mess.isPresent()){
            throw new MessagesExceptionHandler("Message not found");
        }

        Messages message = mess.get();
        message.setIsDeleted(true);
        messagesRepository.save(message);
    }

    public List<Messages> getParticularUserMessages(GetParticularUserMessagesRequest getParticularUserMessagesRequest) throws MessagesExceptionHandler {

        Optional <Users> user = usersRepository.findById(getParticularUserMessagesRequest.getUserId());

        if(!user.isPresent()){
            throw new MessagesExceptionHandler("User not Found");
        }
        
        Optional <Messages> sender = messagesRepository.findBySender(user.get());
        Optional <Messages> receiver = messagesRepository.findByReceiver(user.get());

        if(!sender.isPresent() && !receiver.isPresent()){
            throw new MessagesExceptionHandler("Messages for user not Found");
        }
        
        Pageable find = PageRequest.of(getParticularUserMessagesRequest.getPage(), getParticularUserMessagesRequest.getItems());
        Page <Messages> messages = messagesRepository.findAllUserMessagesWithPagination(user.get(), user.get(), getParticularUserMessagesRequest.getIsDeleted(), find);

        return messages.getContent();
    }

    public List<Messages> getParticularChatMessages(GetParticularChatMessagesRequest getParticularChatMessagesRequest) throws MessagesExceptionHandler {
        
        Pageable find = PageRequest.of(getParticularChatMessagesRequest.getPage(), getParticularChatMessagesRequest.getItems());
        Page <Messages> messages = messagesRepository.findByChat(getParticularChatMessagesRequest.getChat(), find);

        return messages.getContent();
    }
}