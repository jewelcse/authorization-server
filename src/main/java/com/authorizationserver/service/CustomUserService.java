package com.authorizationserver.service;

import com.authorizationserver.dto.RegisterDto;
import com.authorizationserver.model.User;

public interface CustomUserService {
    User registerUser(RegisterDto registerDto);

    User userDetailsByUsername(String username);
}
