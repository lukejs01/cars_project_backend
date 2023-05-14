package com.car.project.car.project.exception;

import com.jayway.jsonpath.InvalidCriteriaException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.InstanceAlreadyExistsException;
import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse errorHandler(NullPointerException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("400");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = {ChangeSetPersister.NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse errorHandler(ChangeSetPersister.NotFoundException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("404");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = {InvalidCriteriaException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse errorHandler() {
        ErrorResponse err = new ErrorResponse();
        err.setMessage("Invalid username and password");
        err.setErrorCode("401");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = {InstanceAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse errorHandlerUsername(String message) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(message);
        err.setErrorCode("403");
        err.setTime(new Date());
        return err;
    }
}
