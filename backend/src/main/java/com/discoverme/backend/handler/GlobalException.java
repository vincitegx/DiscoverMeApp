package com.discoverme.backend.handler;

public class GlobalException extends RuntimeException{

    private String message;
    private String code;
    public GlobalException(String message) {
        super(message);
    }
}
