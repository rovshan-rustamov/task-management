package com.example.taskmanagement.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    public final ErrorCode errorCode;
    public final transient Object[] arguments;

    public BadRequestException(ErrorCode errorCode, Object... args) {
        this.errorCode = errorCode;
        this.arguments = args == null ? new Object[0] : args;
    }

}
