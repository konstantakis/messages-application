package com.konstantakis.messages.service.mapstruck;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.dto.MessageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessagesMapper {
    Message messageDTOToMessage(MessageDTO messageDTO);
}
