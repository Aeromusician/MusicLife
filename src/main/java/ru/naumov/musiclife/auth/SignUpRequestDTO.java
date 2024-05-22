package ru.naumov.musiclife.auth;

import lombok.Data;

@Data
public class SignUpRequestDTO {

    private String username;
    private String email;
    private String password;
    private UserType userType;

}
