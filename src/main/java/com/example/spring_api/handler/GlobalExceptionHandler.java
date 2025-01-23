package com.example.spring_api.handler;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.base_domain.dto.model.Error;
import com.example.base_domain.dto.response.BaseResponse;
import com.example.base_domain.repository.ErrorRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorRepository errorRepository;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        BaseResponse response = new BaseResponse();
        Optional<Error> temp = errorRepository.findByCode(ex.getMessage());
        response.setResponseCode(temp.get().getCode());
        response.setResponseMessage(temp.get().getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Getter
    @Setter
    public static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

    }
}