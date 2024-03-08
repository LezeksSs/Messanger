package com.example.MessangerServer.controller;

import com.example.MessangerServer.dto.CreateChatRequest;
import com.example.MessangerServer.dto.JwtAuthenticationResponse;
import com.example.MessangerServer.dto.SingUpRequest;
import com.example.MessangerServer.entity.Chat;
import com.example.MessangerServer.service.ChatService;
import com.example.MessangerServer.service.MessageService;
import com.example.MessangerServer.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "Примеры", description = "Примеры запросов с разными правами доступа")
public class ExampleController {
    private final ChatService chatService;
    private final MessageService messageService;

    @GetMapping
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return "Hello, world!";
    }

    @Operation(summary = "Создание чата")
    @PostMapping("/chat")
    public void singUp(@RequestBody @Valid CreateChatRequest request) {
        chatService.createChat(request);
    }

    @Operation(summary = "Создание чата")
    @GetMapping("/chat/{id}")
    public Chat getChat(@PathVariable long id) {
        return chatService.getChat(id);
    }

    @Operation(summary = "Получение чатов текущего пользователя")
    @GetMapping("/chat")
    public List<Chat> getChats() {
        return chatService.getChats();
    }

    @PostMapping("/chat/{id}/message")
    public void addMessage(@PathVariable long id) {
        messageService.addMessage(chatService.getChat(id), "Test");
    }
}
