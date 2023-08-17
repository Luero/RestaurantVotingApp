package ru.javaops.restauranvotingapp.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class ConflictRequestException extends AppException {
    public ConflictRequestException(String msg) {
        super(HttpStatus.CONFLICT, msg, ErrorAttributeOptions.of(MESSAGE));
    }
}
