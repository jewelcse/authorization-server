package com.authorizationserver.service;

import com.authorizationserver.dto.CustomerDto;
import com.authorizationserver.dto.RegisterDto;
import com.authorizationserver.model.User;

public interface CustomUserService {
    User registerUser(RegisterDto registerDto);
    User registerUser(CustomerDto customerDto);
    User userDetailsByUsername(String username);

}
