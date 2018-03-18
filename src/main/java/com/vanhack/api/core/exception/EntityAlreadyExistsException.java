package com.vanhack.api.core.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends UserException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getErrorCode() {
        return HttpStatus.CONFLICT;
    }
}
