package com.konstantakis.messages.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * add
 *  javadoc
 */
@Builder
@Getter
@EqualsAndHashCode
public class Message {
    @Setter
    private Integer id;

    @NotNull(message = "Content can't be null")
    @NotBlank(message = "Content can't be empty")
    @Size(max = 256, message = "Content can't can't exceed 256 characters")
    private String content;

    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;

    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate changedOn;
}
