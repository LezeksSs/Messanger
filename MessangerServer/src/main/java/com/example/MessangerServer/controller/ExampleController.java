package com.example.MessangerServer.controller;


import com.example.MessangerServer.service.ChatService;
import com.example.MessangerServer.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

}
