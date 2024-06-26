package com.example.MessangerServer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание чата с указанным именем пользователя")
public class CreateChatRequest {
    @Schema(description = "Имя пользователя с которым нужно создать чат", example = "Lezeks")
    @Size(min = 2, max = 20, message = "Username must be from 2 to 20 characters")
    @NotBlank(message = "Username field can not be blank")
    private String userTo;
}
