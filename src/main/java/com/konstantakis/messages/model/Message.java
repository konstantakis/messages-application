package com.konstantakis.messages.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Message object used on api and service layers
 */
@Builder
@Getter
@EqualsAndHashCode
public class Message implements Serializable {
    @Setter
    private Long id;

    @Setter
    private String content;

    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;

    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate changedOn;
}
