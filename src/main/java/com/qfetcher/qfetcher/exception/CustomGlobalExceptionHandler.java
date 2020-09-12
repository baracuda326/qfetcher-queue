package com.qfetcher.qfetcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //****************************************************************************
    @ExceptionHandler({DataResourceException.class, NoValidFieldException.class})
    public void handleBadRequestException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    //****************************************************************************

    @ExceptionHandler(HttpConnectionException.class)
    public void handleConflictException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value());
    }

    //****************************************************************************
    @ExceptionHandler(ConnectionTimeOutException.class)
    public void handleConnectionTimeOutException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.REQUEST_TIMEOUT.value());
    }

    //****************************************************************************
}
