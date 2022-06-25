package com.konstantakis.messages.model.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * TODO
 * add
 *  javadoc
 */
@Builder
@Getter
@Entity
@Table(name="messages")
public class MessageDTO {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @Column
    private Date createdOn;

    @Column
    private Date changedOn;

    @Column
    private Timestamp dbRecordCreatedOn;

    @Column
    private Timestamp dbRecordChangedOn;
}
