package com.konstantakis.messages.model;

import lombok.Builder;
import lombok.Getter;

/**
 * TODO
 * add
 *  javadoc
 */

@Builder
@Getter
public class ErrorResponse {
    private String error;
    private String message;
    // TODO add traceId implementation
    private String traceId;
}
