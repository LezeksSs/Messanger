package com.example.MessangerServer.service;

import com.example.MessangerServer.dto.CreateChatRequest;
import com.example.MessangerServer.entity.Chat;
import com.example.MessangerServer.entity.User;
import com.example.MessangerServer.repository_dao.ChatRepository;
import com.example.MessangerServer.repository_dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public ResponseEntity<?> createChat(CreateChatRequest request) {

        if (chatRepository.existsByName(request.getUserTo())) {
//            throw new RuntimeException("Чат с таким пользователем уже существует");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chat with that user already exist");
        }
        if (request.getUserTo().equals(userService.getCurrentUser().getNickname())) {
//            throw new RuntimeException("Нельзя создать чат с самим собой");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can not make chat with yourself");
        }

        try {
            userRepository.findByNickname(request.getUserTo()).orElseThrow(() -> new UsernameNotFoundException("No user"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        var chat = Chat.builder()
                .name(request.getUserTo())
                .build();

        chat.addUserToChat(userService.getCurrentUser());
        chat.addUserToChat(userRepository.findUserByNickname(request.getUserTo()));
        saveChat(chat);
        return ResponseEntity.ok("Ok");
    }

    public List<Chat> getChats() {
        return userService.getCurrentUser().getChats();
    }

    public Chat getChat(long id) {
        return chatRepository.getChatById(id);
    }

    public void deleteChat(long id) {
        chatRepository.deleteById(id);
    }
}
