package com.example.mangodbcookiememorycache.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailed extends AuthenticationException {
    public AuthenticationFailed(String message){
        super(message);
    }
}
