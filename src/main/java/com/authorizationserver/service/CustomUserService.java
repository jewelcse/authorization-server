package com.authorizationserver.service;

import com.authorizationserver.dto.CustomerRegisterDto;
import com.authorizationserver.dto.DeliveryManRegisterDto;
import com.authorizationserver.dto.SellerRegisterDto;
import com.authorizationserver.model.User;

public interface CustomUserService {
    User registerUser(SellerRegisterDto registerDto);
    User registerUser(CustomerRegisterDto customerDto);
    User registerUser(DeliveryManRegisterDto deliveryManRegisterDto);
    User userDetailsByUsername(String username);

}
