package com.softdesign.exception;


public class BusinessException extends RuntimeException {
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}