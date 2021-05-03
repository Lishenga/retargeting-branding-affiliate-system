package com.authorization.authentication.utils;


import com.authorization.authentication.requests.users.RegisterUserRequest;
import com.authorization.authentication.responses.users.RegisterUserResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @FeignClient(name="currency-exchange", url="localhost:8000")
@FeignClient(name="retargeting-branding-service")
public interface RemoteCallsProxy {
    @RequestMapping(value = "/retargetingandbranding/registeruser", method = RequestMethod.POST)
    public RegisterUserResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest);
}
