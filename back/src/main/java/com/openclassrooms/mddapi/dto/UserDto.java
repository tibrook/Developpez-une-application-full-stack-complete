package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String username;
    private String password;
}