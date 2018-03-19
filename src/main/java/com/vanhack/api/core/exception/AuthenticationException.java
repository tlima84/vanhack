package com.vanhack.api.core.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends UserException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getErrorCode() {
        return HttpStatus.FORBIDDEN;
    }
}
