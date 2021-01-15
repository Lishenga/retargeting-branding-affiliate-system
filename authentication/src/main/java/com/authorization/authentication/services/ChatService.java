package com.authorization.authentication.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.authorization.authentication.exceptions.chat.ChatExceptionHandler;
import com.authorization.authentication.models.Chat;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.ChatRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.chat.CreateChatRequest;
import com.authorization.authentication.requests.chat.DeleteChatRequest;
import com.authorization.authentication.requests.chat.GetParticularChatRequest;
import com.authorization.authentication.requests.chat.GetParticularUserChatsRequest;
import com.authorization.authentication.responses.chat.GeneralChatResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
	private RedisTemplate<String, GeneralChatResponse> redisCreateChatResponse;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsersRepository usersRepository;

    private String error = "Chat not found";

    private String userError = "User not found";

    private GeneralChatResponse generalChatResponse = new GeneralChatResponse();
    
    public void createChat(CreateChatRequest createChatRequest) throws ChatExceptionHandler {
        Optional <Users> userOne = usersRepository.findById(createChatRequest.getUserOne());
        Optional <Users> initiator = usersRepository.findById(createChatRequest.getInitiator());
        Optional <Users> userTwo = usersRepository.findById(createChatRequest.getUserTwo());

        if(!userOne.isPresent() || !userTwo.isPresent() || !initiator.isPresent()){
            generalChatResponse.setData(null);
            generalChatResponse.setStatus(500);
            generalChatResponse.setMessage(this.userError);
            redisCreateChatResponse.convertAndSend("createChatResponse", generalChatResponse);
            throw new ChatExceptionHandler(this.userError);
        }

        Chat chat = new Chat();
        chat.setUserOne(userOne.get());
        chat.setUserTwo(userTwo.get());
        chat.setCreatedBy(initiator.get());
        chat.setIsDeleted(false);
        chat.setCreatedAt(LocalDateTime.now());
        chat.setUpdatedAt(LocalDateTime.now());
        chatRepository.save(chat);

        generalChatResponse.setData(chat);
        generalChatResponse.setStatus(200);
        generalChatResponse.setMessage("success");
        redisCreateChatResponse.convertAndSend("createChatResponse", generalChatResponse);
    }

    public List <Chat> getAllChats(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Chat> chats = chatRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return chats.getContent();
    }

    public void deleteChat(DeleteChatRequest deleteChatRequest) throws ChatExceptionHandler {

        Optional <Chat> find = chatRepository.findById(deleteChatRequest.getChatId());

        if(!find.isPresent()){
            throw new ChatExceptionHandler(this.error);
        }

        Chat chat = find.get();
        chat.setIsDeleted(true);
        chatRepository.save(chat);
    }

    public Chat getParticularChat(GetParticularChatRequest getParticularChatRequest) throws ChatExceptionHandler {

        Optional <Chat> chat = chatRepository.findById(getParticularChatRequest.getChatId());

        if(!chat.isPresent()){
            throw new ChatExceptionHandler(this.error);
        }

        return chat.get();
    }

    public List <Chat> getParticularUserChats(GetParticularUserChatsRequest getParticularUserChatsRequest) throws ChatExceptionHandler {

        Optional <Users> user = usersRepository.findById(getParticularUserChatsRequest.getUserId());

        if(!user.isPresent()){
            throw new ChatExceptionHandler(this.userError);
        }

        Pageable find = PageRequest.of(getParticularUserChatsRequest.getPage(), getParticularUserChatsRequest.getItems());
        Page <Chat> chats = chatRepository.findUserChats(user.get(), getParticularUserChatsRequest.getIsDeleted(), find);

        return chats.getContent();
    }
}