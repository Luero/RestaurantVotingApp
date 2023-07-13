package ru.javaops.restauranvotingapp.config;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.RequestDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.javaops.restauranvotingapp.error.AppException;

import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final ErrorAttributes errorAttributes;

    //https://stackoverflow.com/questions/56840409/how-to-change-status-code-in-spring-boot-error-response

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> appException(AppException ex, WebRequest request) {
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNPROCESSABLE_ENTITY.value(), RequestAttributes.SCOPE_REQUEST);
        final Map<String, Object> body = errorAttributes.getErrorAttributes(request, ex.getOptions());

        return handleExceptionInternal(ex, errorAttributes, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

/*    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> appException(BindException ex, WebRequest request) {
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
        final Map<String, Object> body = errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());

        return handleExceptionInternal(ex, errorAttributes, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }*/

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> appException(RuntimeException ex, WebRequest request) {
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value(), RequestAttributes.SCOPE_REQUEST);
        final Map<String, Object> body = errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());

        return handleExceptionInternal(ex, errorAttributes, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
