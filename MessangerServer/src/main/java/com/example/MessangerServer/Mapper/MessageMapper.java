package com.example.MessangerServer.Mapper;

import com.example.MessangerServer.dto.MessageResponse;
import com.example.MessangerServer.dto.UserResponse;
import com.example.MessangerServer.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageResponse messageToDTO(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .text(message.getText())
                .user(new UserResponse(message.getUser().getId(), message.getUser().getNickname(), message.getUser().getEmail()))
                .sending_date(message.getSending_date())
                .sending_status(message.isSending_status())
                .read_status(message.isRead_status())
                .build();
    }
}
