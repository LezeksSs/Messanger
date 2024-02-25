package com.example.MessangerServer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SingInRequest {
    @Schema(description = "Имя пользователя", example = "Lezeks")
    @Size(min = 2, max = 20, message = "Имя пользователя должно содержать от 2 до 20 символов")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String nickname;

    @Schema(description = "Пароль", example = "123456qwerty")
    @Size(min = 8, max = 50, message = "Длина пароля должна быть от 8 до 50 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
