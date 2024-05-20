package com.example.MessangerServer.service;

import com.example.MessangerServer.Mapper.MessageMapper;
import com.example.MessangerServer.dto.MessageRequest;
import com.example.MessangerServer.dto.MessageResponse;
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

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserService userService;
    private final MessageMapper mapper;


    @Autowired
    private EntityManager entityManager;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    public MessageResponse addMessage(Chat chat, MessageRequest request, String username) {
        var message = Message.builder()
                .text(request.getText())
                .chat(chat)
                .user(userService.getByNickname(username))
                .build();
        chat.addMessageToChat(message);
        saveMessage(message);


        return mapper.messageToDTO(message);
    }

    public List<MessageResponse> getMessage(long id) {
        return chatRepository.getChatById(id).getMessages().stream().map(mapper::messageToDTO).collect(toList());
    }

    @Transactional
    public void deleteMessage(long id) {
        Query query = entityManager.createQuery("delete from Message " +
                "where id =:messageId");
        query.setParameter("messageId", id);
        query.executeUpdate();
    }
}
