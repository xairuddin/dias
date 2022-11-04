package com.example.FirstSecurityApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthenticationDTO {
    @NotEmpty(message = "The name should not be empty")
    @Size(min = 2, max = 100, message = "The name should be between 2 and 100 symbols")
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
