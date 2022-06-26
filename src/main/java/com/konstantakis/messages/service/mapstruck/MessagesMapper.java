package com.konstantakis.messages.service.mapstruck;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.MessageRequestBody;
import com.konstantakis.messages.model.dto.MessageDTO;
import org.mapstruct.Mapper;

/**
 * Object mapper
 */
@Mapper(componentModel = "spring")
public interface MessagesMapper {
    /**
     * Translates a {@link MessageDTO} to a {@link Message}
     * @param messageDTO message
     * @return message
     */
    Message messageDTOToMessage(MessageDTO messageDTO);

    /**
     * Translates a {@link Message} to a {@link MessageDTO}
     * @param message message
     * @return messageDTO
     */
    MessageDTO messageRequestBodyToMessageDTO(MessageRequestBody message);
}
