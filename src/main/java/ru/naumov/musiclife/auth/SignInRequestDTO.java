package ru.naumov.musiclife.auth;

import lombok.Data;

@Data
public class SignInRequestDTO {
    private String username;
    private String password;
}
