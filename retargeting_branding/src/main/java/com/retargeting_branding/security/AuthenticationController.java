package com.retargeting_branding.security;

import com.retargeting_branding.requests.users.RegisterUserRequest;
import com.retargeting_branding.responses.users.RegisterUserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    
    @Autowired
	private AuthenticationService authenticationService;

	private RegisterUserResponse registerUserResponse = new RegisterUserResponse();

    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public RegisterUserResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest) {

		authenticationService.registerUser(registerUserRequest);

		registerUserResponse.setMessage("Success");
		registerUserResponse.setStatus(200);

		return registerUserResponse;
	}

	@RequestMapping(value = "/heathcheck", method = RequestMethod.GET)
	public Integer healthCheck() {
		return 200;
	}
}