package com.typeqast.meterreadings.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityExistsException extends RuntimeException{

    public EntityExistsException(String message) {
        super(message);
    }
}
