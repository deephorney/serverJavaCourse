package ru.mirea.platform.entity;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
}
