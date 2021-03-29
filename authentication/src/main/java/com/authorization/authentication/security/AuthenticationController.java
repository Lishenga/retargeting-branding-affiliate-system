package com.authorization.authentication.security;

import java.util.Map;
import java.util.Optional;

import com.authorization.authentication.exceptions.security.SecurityExceptionHandler;
import com.authorization.authentication.helpers.EmailService;
import com.authorization.authentication.models.Clients;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.ClientsRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.accesslogs.CreateAccessLogsRequest;
import com.authorization.authentication.requests.users.RegisterUserRequest;
import com.authorization.authentication.requests.emails.EmailRequest;
import com.authorization.authentication.requests.authentication.UserAuthenticationRequest;
import com.authorization.authentication.responses.authentication.AuthenticationResponse;
import com.authorization.authentication.responses.users.RegisterUserResponse;
import com.authorization.authentication.responses.authentication.UserAuthenticationResponse;
import com.authorization.authentication.services.AccessLogsService;
import com.authorization.authentication.services.AddressService;
import com.authorization.authentication.services.TokenService;
import com.authorization.authentication.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

	@Value("${system-config.email.sender}")
    private String emailSender;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private AccessLogsService accessLogsService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ClientsRepository clientsRepository;

	private UserAuthenticationResponse userAuthenticationResponse = new UserAuthenticationResponse();

	private RegisterUserResponse registerUserResponse = new RegisterUserResponse();

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse>  createAuthenticationToken(@RequestHeader("clientId") String clientId, @RequestHeader("clientSecret") String clientSecret, @RequestHeader("clientSecret") String deviceIp, @RequestHeader("clientSecret") String deviceId, @RequestHeader("clientSecret") String location, @RequestHeader("clientSecret") String browserAgent) throws SecurityExceptionHandler  {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(clientSecret , clientId)
			);
		}
		catch (BadCredentialsException e) {
			throw new SecurityExceptionHandler("Incorrect client id or client secret");
		}

		final UserDetails userDetails = authenticationService.clientAuthentication(clientId, clientSecret);

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Clients client = clientsRepository.findByClientSecret(clientSecret);

		CreateAccessLogsRequest createAccessLogsRequest = new CreateAccessLogsRequest(deviceId, deviceIp, location, null, browserAgent, client.getId(), null);

		accessLogsService.saveAccessLogs(createAccessLogsRequest);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@RequestMapping(value = "/authenticateuser", method = RequestMethod.POST)
	public ResponseEntity<UserAuthenticationResponse> authenticateUser(@RequestBody UserAuthenticationRequest userAuthenticateUserRequest, @RequestHeader("clientId") String clientId, @RequestHeader("clientSecret") String clientSecret, @RequestHeader("clientSecret") String deviceIp, @RequestHeader("clientSecret") String deviceId, @RequestHeader("clientSecret") String location, @RequestHeader("clientSecret") String browserAgent) throws SecurityExceptionHandler {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userAuthenticateUserRequest.getEmail(), userAuthenticateUserRequest.getEmail())
			);
		}
		catch (BadCredentialsException e) {
			throw new SecurityExceptionHandler("Kindly provide the right credentials");
		}

		if(clientId == null || clientSecret == null){
			throw new SecurityExceptionHandler("Kindly provide client id and client secret in the request headers");
		}

		final UserDetails userDetails = authenticationService.userAuthentication(clientId, clientSecret, userAuthenticateUserRequest);

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Map<String, Long> tokenDetails = tokenService.saveToken(jwt, userDetails.getUsername());

		Optional <Users> user = usersRepository.findById(tokenDetails.get("user"));

		if(!user.isPresent()){
			throw new SecurityExceptionHandler("User not found");
		}

		Clients client = clientsRepository.findByClientSecret(clientSecret);

		CreateAccessLogsRequest createAccessLogsRequest = new CreateAccessLogsRequest(deviceId, deviceIp, location, tokenDetails.get("user"), browserAgent, client.getId(), tokenDetails.get("token"));

		accessLogsService.saveAccessLogs(createAccessLogsRequest);

		userAuthenticationResponse.setData(user.get());
		userAuthenticationResponse.setMessage("Success");
		userAuthenticationResponse.setStatus(200);
		userAuthenticationResponse.setToken(jwt);

		return ResponseEntity.ok(userAuthenticationResponse);
	}

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest, @RequestHeader("clientId") String clientId, @RequestHeader("clientSecret") String clientSecret, @RequestHeader("deviceIp") String deviceIp, @RequestHeader("deviceId") String deviceId, @RequestHeader("location") String location, @RequestHeader("browserAgent") String browserAgent) throws SecurityExceptionHandler, MailjetException, MailjetSocketTimeoutException {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(clientSecret , clientId)
			);
		}
		catch (BadCredentialsException e) {
			throw new SecurityExceptionHandler("Incorrect client id or client secret");
		}

		Users user = authenticationService.registerUser(clientId, clientSecret, registerUserRequest);

		addressService.saveAddress(user, registerUserRequest);

		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setName(registerUserRequest.getFirstName() +registerUserRequest.getLastName());
		emailRequest.setTo(registerUserRequest.getEmail());
		emailRequest.setFrom(emailSender);
		emailRequest.setSubject("Thank you for registering with us");
		
		emailService.sendEmail(emailRequest);

		Clients client = clientsRepository.findByClientSecret(clientSecret);

		CreateAccessLogsRequest createAccessLogsRequest = new CreateAccessLogsRequest(deviceId, deviceIp, location, null, browserAgent, client.getId(), null);

		accessLogsService.saveAccessLogs(createAccessLogsRequest);

		webClientBuilder.build().post().uri("http://retargeting-branding-service/registeruser").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Mono.just(registerUserRequest), RegisterUserRequest.class)
		.retrieve().bodyToMono(RegisterUserResponse.class).block();
		
		registerUserResponse.setMessage("Success");
		registerUserResponse.setStatus(200);

		return ResponseEntity.ok(registerUserResponse);
	}

	@RequestMapping(value = "/heathcheck", method = RequestMethod.GET)
	public Integer healthCheck() {
		return 200;
	}
}