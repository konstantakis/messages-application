package com.konstantakis.messages.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * TODO
 * add
 *  javadoc
 */
@Builder
@Getter
@EqualsAndHashCode
public class Message {
    @Setter
    private Integer id;
    private String content;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdOn;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate changedOn;
}
