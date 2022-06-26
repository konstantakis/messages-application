package com.konstantakis.messages.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestBody implements Serializable {
    @Setter
    @NotNull(message = "Content can't be null")
    @NotBlank(message = "Content can't be empty")
    @Size(max = 256, message = "Content can't can't exceed 256 characters")
    private String content;
}
