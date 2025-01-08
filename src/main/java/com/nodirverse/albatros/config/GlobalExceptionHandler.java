package com.nodirverse.albatros.config;

import com.nodirverse.albatros.dto.AppErrorDto;
import com.nodirverse.albatros.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<AppErrorDto> dataNotFoundExceptionHandler(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AppErrorDto(e.getMessage(), 404));
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<AppErrorDto> handleDataAlreadyExistsException(DataAlreadyExistsException  e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new AppErrorDto(e.getMessage(), 409));
    }

    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<AppErrorDto> notEnoughFundsExceptionHandler(NotEnoughFundsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppErrorDto(e.getMessage(), 401));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<AppErrorDto> wrongPasswordException(WrongPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppErrorDto(e.getMessage(), 400));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppErrorDto> handleAccessDeniedException(AccessDeniedException ex) {
        String errorMessage = "Ruxsat rad etildi: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AppErrorDto(errorMessage, 403));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<AppErrorDto> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppErrorDto(e.getMessage(), 401));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<AppErrorDto> bindExceptionHandler(BindException e) {
        e.getAllErrors().get(0).getDefaultMessage();

        StringBuilder errors = new StringBuilder();
        e.getAllErrors().forEach(error -> {
            errors.append(error.getDefaultMessage()).append("\n");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppErrorDto(errors.toString(), 400));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "JSON formatida xatolik: " + ex.getLocalizedMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception ex) {
//        String errorMessage = "Serverda xatolik yuz berdi: " + ex.getMessage();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//    }

//    @ExceptionHandler(Throwable.class)
//    public ResponseEntity<AppErrorDto> handleThrowable(Throwable ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AppErrorDto("Noma'lum xatolik yuz berdi", 500));
//    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<AppErrorDto> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        String errorMessage = "Media type not supported: " + ex.getContentType();
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new AppErrorDto(errorMessage, 415));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<AppErrorDto> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String errorMessage = "Method not supported: " + ex.getMethod();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new AppErrorDto(errorMessage, 405));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppErrorDto(errors.toString().trim(), 400));
    }

}
