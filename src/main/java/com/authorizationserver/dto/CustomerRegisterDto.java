package com.authorizationserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
}
