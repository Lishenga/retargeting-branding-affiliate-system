package com.retargeting_branding.security;

import java.util.ArrayList;
import java.util.Optional;

import com.retargeting_branding.models.Clients;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.repositories.ClientsRepository;
import com.retargeting_branding.repositories.UsersRepository;
import com.retargeting_branding.requests.users.RegisterUserRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private UsersRepository usersRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public UserDetails loadUserByUsername(String identifier){

        try {
            
            Clients client = clientsRepository.findByClientSecret(identifier);
                    
            return new User(client.getClientSecret(), client.getClientId(),
                    new ArrayList<>());
        } catch (Exception e) {
           
            Optional <Users> users = usersRepository.findByEmail(identifier);

            if(!users.isPresent()){
                throw new UsernameNotFoundException(identifier, e);
            }
                    
            return new User(users.get().getEmail(), users.get().getEmail(),
                    new ArrayList<>());
        }
    }

    public Users registerUser(RegisterUserRequest registerUserRequest) {

        registerUserRequest.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        Users users = new Users();
        users.userCreate(registerUserRequest);
        usersRepository.save(users);

        return users;
    }
}