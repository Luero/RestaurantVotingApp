package ru.javaops.restauranvotingapp.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.javaops.restauranvotingapp.error.AppException;

import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final ErrorAttributes errorAttributes;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> appException(AppException ex, WebRequest request) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, ex.getOptions());
        HttpStatusCode statusCode = ex.getStatusCode();
        body.put("status", statusCode.value());
        body.put("error", ex.getClass().getSimpleName());
        return ResponseEntity.status(statusCode).body(body);
    }
}
