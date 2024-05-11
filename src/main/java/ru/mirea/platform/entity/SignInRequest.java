package ru.mirea.platform.entity;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
