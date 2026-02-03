package com.gs.f1bet.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ApiError handleGenericException(Exception ex) {
        var errorToken = "err_" + UUID.randomUUID().toString().substring(0, 8);
        return new ApiError(
                "INTERNAL_ERROR",
                "An internal server error occurred: " + ex.getMessage(),
                errorToken);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchElementException.class})
    public ApiError handleNotFound() {
        var errorToken = "err_" + UUID.randomUUID().toString().substring(0, 8);
        return new ApiError(
                "NOT_FOUND",
                "The requested resource was not found",
                errorToken);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ApiError handleIllegalArgument(RuntimeException ex) {
        var errorToken = "err_" + UUID.randomUUID().toString().substring(0, 8);
        return new ApiError(
                "BAD_REQUEST",
                "The request is malformed: " + ex.getMessage(),
                errorToken);
    }

    public record ApiError(
            @Schema(example = "NOT_FOUND")
            String code,

            @Schema(example = "The requested resource was not found")
            String message,

            @Schema(example = "err_14e70e3b")
            String token) {
    }
}