package com.authorization.authentication.security;

import java.util.ArrayList;
import java.util.Optional;

import com.authorization.authentication.exceptions.security.SecurityExceptionHandler;
import com.authorization.authentication.models.Clients;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.ClientsRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.users.RegisterUserRequest;
import com.authorization.authentication.requests.authentication.UserAuthenticationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ClientsRepository clientRepository;

    @Autowired
    private UsersRepository usersRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String error = "Client not found";
    
    @Override
    public UserDetails loadUserByUsername(String identifier){

        try {
            
            Clients client = clientRepository.findByClientSecret(identifier);
                    
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

    public UserDetails clientAuthentication(String clientId, String clientSecret) throws SecurityExceptionHandler {

        Optional <Clients> client = clientRepository.findByClientIdAndClientSecret(clientId, clientSecret);

        if(!client.isPresent()){
            throw new SecurityExceptionHandler(this.error);
        }

        return new User(client.get().getClientSecret(), client.get().getClientId(),
                new ArrayList<>());
    }

    public UserDetails userAuthentication(String clientId, String clientSecret, UserAuthenticationRequest userAuthenticateUserRequest) throws SecurityExceptionHandler {
       
        Optional <Clients> client = clientRepository.findByClientIdAndClientSecret(clientId, clientSecret);
        Optional <Users> users = usersRepository.findByEmail(userAuthenticateUserRequest.getEmail());

        if(!client.isPresent()){
            throw new SecurityExceptionHandler(this.error);
        }

        if(!users.isPresent()){
            throw new SecurityExceptionHandler("User not Found");
        }

        boolean matches = passwordEncoder.matches(userAuthenticateUserRequest.getPassword(), users.get().getPassword());

        if (!matches) {
			throw new SecurityExceptionHandler("Invalid password");
		}
        
        return new User(users.get().getEmail(), users.get().getEmail(),
                new ArrayList<>());
    }

    public Users registerUser(String clientId, String clientSecret, RegisterUserRequest registerUserRequest) throws SecurityExceptionHandler {

        Optional <Clients> client = clientRepository.findByClientIdAndClientSecret(clientId, clientSecret);

        if(!client.isPresent()){
            throw new SecurityExceptionHandler(this.error);
        }

        registerUserRequest.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        Users users = new Users();
        users.userCreate(registerUserRequest);
        usersRepository.save(users);

        return users;
    }
}