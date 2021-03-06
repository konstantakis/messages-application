package com.konstantakis.messages.repository;

import com.konstantakis.messages.model.dto.MessageDTO;
import org.springframework.data.repository.CrudRepository;

/**
 * Message repository
 */
public interface MessageRepository extends CrudRepository<MessageDTO, Long> {
}
