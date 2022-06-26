package com.konstantakis.messages.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TODO
 * add
 *  javadoc
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name="messages")
public class MessageDTO implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @Column
    private LocalDate createdOn;

    @Column
    private LocalDate changedOn;

    @Column
    private LocalDateTime dbRecordCreatedOn;

    @Column
    private LocalDateTime dbRecordChangedOn;
}
