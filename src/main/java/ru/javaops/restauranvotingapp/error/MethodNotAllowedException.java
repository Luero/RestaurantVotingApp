package ru.javaops.restauranvotingapp.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class MethodNotAllowedException extends AppException {
    public MethodNotAllowedException(String msg) {
        super(HttpStatus.METHOD_NOT_ALLOWED, msg, ErrorAttributeOptions.of(MESSAGE));
    }
}
