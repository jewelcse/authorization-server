package com.authorizationserver.controller;


import com.authorizationserver.dto.CustomerRegisterDto;
import com.authorizationserver.dto.DeliveryManRegisterDto;
import com.authorizationserver.dto.SellerRegisterDto;
import com.authorizationserver.model.User;
import com.authorizationserver.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth-service")
@CrossOrigin
public class UserController {

    private final CustomUserService customUserService;


    @Autowired
    public UserController(CustomUserService customUserService){
        this.customUserService = customUserService;
    }

    @PostMapping("/seller/registration")
    public ResponseEntity<?> sellerRegistration(@RequestBody SellerRegisterDto registerDto){
        return new ResponseEntity<>(customUserService.registerUser(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/customer/registration")
    public ResponseEntity<?> customerRegistration(@RequestBody CustomerRegisterDto customerDto){
        return new ResponseEntity<>(customUserService.registerUser(customerDto), HttpStatus.CREATED);
    }

    @PostMapping("/delivery-man/registration")
    public ResponseEntity<?> deliveryManRegistration(@RequestBody DeliveryManRegisterDto deliveryManRegisterDto){
        return new ResponseEntity<>(customUserService.registerUser(deliveryManRegisterDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserDetailsByUsername(@PathVariable String username){
        return new ResponseEntity<User>(customUserService.userDetailsByUsername(username),HttpStatus.OK);
    }

    @GetMapping("/get/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(customUserService.getUsers(),HttpStatus.OK);
    }
    @GetMapping("/get/delivery/mens")
    public ResponseEntity<List<User>> getDeliveryMens(){
        return new ResponseEntity<>(customUserService.getDeliverymanProfile(),HttpStatus.OK);
    }
}
