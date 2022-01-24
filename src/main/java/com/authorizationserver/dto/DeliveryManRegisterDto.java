package com.authorizationserver.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryManRegisterDto {

    private String firstName;
    private String lastName;
    private String mobile;
    private String username;
    private String password;
}
