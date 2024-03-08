package com.example.MessangerServer.service;

import com.example.MessangerServer.entity.Chat;
import com.example.MessangerServer.entity.Message;
import com.example.MessangerServer.repository_dao.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    public Message addMessage(Chat chat, String text) {
        var message = Message.builder()
                .text(text)
                .build();
        chat.addMessageToChat(message);

        return saveMessage(message);
    }
}
