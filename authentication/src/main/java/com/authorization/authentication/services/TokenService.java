package com.authorization.authentication.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.authorization.authentication.exceptions.security.SecurityExceptionHandler;
import com.authorization.authentication.models.Token;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.TokenRepository;
import com.authorization.authentication.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Map<String, Long> saveToken(String key, String user) throws SecurityExceptionHandler {

        Optional <Users> part = usersRepository.findByEmail(user);

        if(!part.isPresent()){
            throw new SecurityExceptionHandler("User not Found");
        }

        Token token = new Token();
        token.saveToken(key, part.get());
        tokenRepository.save(token);

        Map<String, Long> data = new HashMap<>();
        data.put("token", token.getId());
        data.put("user", part.get().getId());

        return data;
    }
}