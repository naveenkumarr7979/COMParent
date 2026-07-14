package com.microservices.userservice.exception;

import com.microservices.userservice.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ApiResponse<Object>handleInvalidCredentialsException(InvalidCredentialsException e)
    {
        ApiResponse<Object> error=new ApiResponse<>();
        error.setData(null);
        error.setSuccess(false);
        error.setMessage(e.getMessage());
        return error;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse<Object>handleUserNotFoundException(UserNotFoundException e)
    {
        ApiResponse<Object> error=new ApiResponse<>();
        error.setData(null);
        error.setSuccess(false);
        error.setMessage(e.getMessage());
        return error;
    }
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Object> handleGeneralException(RuntimeException ex) {

        ApiResponse<Object> response = new ApiResponse<>();
        response.setData(null);
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        response .setData(null);

        return response;
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ApiResponse<Object> handleUserAlreadyExists(
            UserAlreadyExistsException ex){

        ApiResponse<Object> response=ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return response;

    }

}
