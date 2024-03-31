package com.example.MessangerServer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SingInRequest {
    @Schema(description = "Имя пользователя", example = "Lezeks")
    @Size(min = 2, max = 20, message = "Username must be from 2 to 20 characters")
    @NotBlank(message = "Username field can not be blank")
    private String nickname;

    @Schema(description = "Пароль", example = "123456qwerty")
    @Size(max = 50, message = "Length of the password should not exceed 50 letters")
    @NotBlank(message = "Password field can not be blank")
    private String password;
}
