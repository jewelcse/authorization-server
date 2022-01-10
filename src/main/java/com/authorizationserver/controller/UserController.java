package com.authorizationserver.controller;


import com.authorizationserver.dto.RegisterDto;
import com.authorizationserver.model.User;
import com.authorizationserver.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth-service")
@CrossOrigin
public class UserController {

    private final CustomUserService customUserService;


    @Autowired
    public UserController(CustomUserService customUserService){
        this.customUserService = customUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(customUserService.registerUser(registerDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserDetailsByUsername(@PathVariable String username){
        return new ResponseEntity<User>(customUserService.userDetailsByUsername(username),HttpStatus.OK);
    }
}
