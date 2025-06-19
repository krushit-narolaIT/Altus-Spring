package com.krushit.common.exception;

import com.krushit.common.Message;
import com.krushit.dto.ApiResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuthException(AuthException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(DBException.class)
    public ResponseEntity<?> handleDBException(DBException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO(Message.GENERIC_ERROR));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> handleAppException(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<?> handleMessagingException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO(Message.GENERIC_ERROR));
    }

/*    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Method Not Allowed");
        response.put("message", ex.getMessage());
        response.put("supportedMethods", ex.getSupportedHttpMethods());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }*/

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO(Message.GENERIC_ERROR));
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}