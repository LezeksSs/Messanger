package com.example.MessangerServer.service;

import com.example.MessangerServer.dto.CreateChatRequest;
import com.example.MessangerServer.entity.Chat;
import com.example.MessangerServer.entity.User;
import com.example.MessangerServer.repository_dao.ChatRepository;
import com.example.MessangerServer.repository_dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public Chat createChat(CreateChatRequest request) {

        if (chatRepository.existsByName(request.getUserTo())) {
            throw new RuntimeException("Чат с таким пользователем уже существует");
        }
        if (request.getUserTo().equals(userService.getCurrentUser().getNickname())) {
            throw new RuntimeException("Нельзя создать чат с самим собой");
        }

        var chat = Chat.builder()
                .name(request.getUserTo())
                .build();

        chat.addUserToChat(userService.getCurrentUser());
        chat.addUserToChat(userRepository.findUserByNickname(request.getUserTo()));

        return saveChat(chat);
    }
}
