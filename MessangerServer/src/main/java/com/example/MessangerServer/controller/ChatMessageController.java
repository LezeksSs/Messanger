package com.example.MessangerServer.controller;

import com.example.MessangerServer.dto.CreateChatRequest;
import com.example.MessangerServer.dto.MessageRequest;
import com.example.MessangerServer.dto.MessageResponse;
import com.example.MessangerServer.entity.Chat;
import com.example.MessangerServer.entity.Message;
import com.example.MessangerServer.service.ChatService;
import com.example.MessangerServer.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Tag(name = "Операции с чатом и сообщениями чата")
public class ChatMessageController {
    private final ChatService chatService;
    private final MessageService messageService;

    @Operation(summary = "Создание чата")
    @PostMapping
    public void singUp(@RequestBody @Valid CreateChatRequest request) {
        chatService.createChat(request);
    }

    @Operation(summary = "Получение чатов текущего пользователя")
    @GetMapping
    public List<Chat> getChats() {
        return chatService.getChats();
    }

    @Operation(summary = "Получение чата по id")
    @GetMapping("/{id}")
    public Chat getChat(@PathVariable long id) {
        return chatService.getChat(id);
    }

    @Operation(summary = "Удаление чата")
    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable long id) {
        chatService.deleteChat(id);
    }

    @Operation(summary = "Создание сообщение в чате по его id")
    @PostMapping("/{id}/message")
    public void addMessage(@PathVariable long id, @RequestBody @Valid MessageRequest request) {
        messageService.addMessage(chatService.getChat(id), request);
    }

    @Operation(summary = "Получение сообщений в чате по его id")
    @GetMapping("/{id}/message")
    public List<MessageResponse> getMessage(@PathVariable long id) {
        return messageService.getMessage(id);
    }

    @Operation(summary = "Удаление сообщения в чате по его id")
    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable long id) {
        messageService.deleteMessage(id);
    }
}
