package com.discoverme.backend.project;

public class ProjectProcessingException extends RuntimeException{
    public ProjectProcessingException(String message) {
        super(message);
    }

    public ProjectProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
