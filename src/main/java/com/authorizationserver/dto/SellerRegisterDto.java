package com.authorizationserver.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
