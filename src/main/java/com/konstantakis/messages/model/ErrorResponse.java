package com.konstantakis.messages.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 * add
 *  javadoc
 */

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
    private String error;
    private String message;
    private String traceId;
}
