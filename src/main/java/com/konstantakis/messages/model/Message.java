package com.konstantakis.messages.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * TODO
 * add
 *  javadoc
 */
@Builder
@Getter
public class Message {
    @Setter
    private Integer id;
    private String content;
    private Date createdOn;
    private Date changedOn;
}
