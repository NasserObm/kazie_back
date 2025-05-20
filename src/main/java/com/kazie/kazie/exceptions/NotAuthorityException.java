package com.kazie.kazie.exceptions;

public class NotAuthorityException extends RuntimeException {
    public NotAuthorityException(String message) {
        super(message);
    }
}
