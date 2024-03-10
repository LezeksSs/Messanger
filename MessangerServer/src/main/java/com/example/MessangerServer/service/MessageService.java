package com.example.MessangerServer.service;

import com.example.MessangerServer.dto.MessageRequest;
import com.example.MessangerServer.entity.Chat;
import com.example.MessangerServer.entity.Message;
import com.example.MessangerServer.repository_dao.ChatRepository;
import com.example.MessangerServer.repository_dao.MessageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    @Autowired
    private EntityManager entityManager;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    public Message addMessage(Chat chat, MessageRequest request) {
        var message = Message.builder()
                .text(request.getText())
                .build();
        chat.addMessageToChat(message);

        return saveMessage(message);
    }

    public List<Message> getMessage(long id) {
        return chatRepository.getChatById(id).getMessages();
    }

    @Transactional
    public void deleteMessage(long id) {
        Query query = entityManager.createQuery("delete from Message " +
                "where id =:messageId");
        query.setParameter("messageId", id);
        query.executeUpdate();
    }
}
