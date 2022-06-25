package com.konstantakis.messages.exception;

import com.konstantakis.messages.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * TODO
 * add
 *  javadoc
 *  implementation
 *  tests
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Unexpected exception", ex);
        return ResponseEntity.internalServerError().body(
                ErrorResponse.builder()
                        .error("INTERNAL_SERVER_ERROR")
                        .message("Something went wrong")
                        .traceId("")
                        .build());
    }
}
