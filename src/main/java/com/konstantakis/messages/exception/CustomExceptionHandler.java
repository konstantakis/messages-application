package com.konstantakis.messages.exception;

import com.konstantakis.messages.constants.AppConstants;
import com.konstantakis.messages.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * Custom exception handler
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MessageNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleMessageNotFoundException(MessageNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(404).body(
                ErrorResponse.builder()
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message(AppConstants.MESSAGE_NOT_FOUND)
                        .traceId(MDC.get(AppConstants.TRACE_ID_HEADER))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Bad request", ex);
        ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message(ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")))
                        .traceId(MDC.get(AppConstants.TRACE_ID_HEADER))
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.error("Bad request", ex);
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .error(status.getReasonPhrase())
                        .message(ex.getMessage().split(":")[0])
                        .traceId(MDC.get(AppConstants.TRACE_ID_HEADER))
                        .build());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        log.error("Unexpected exception", ex);
        return ErrorResponse.builder()
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(AppConstants.SOMETHING_WENT_WRONG)
                .traceId(MDC.get(AppConstants.TRACE_ID_HEADER))
                .build();
    }
}
