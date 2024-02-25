package com.example.MessangerServer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на регистрацию")
public class SingUpRequest {

    @Schema(description = "Никнейм пользователя", example = "Lezeks")
    @Size(min = 2, max = 20, message = "Имя пользователя должно содержать от 2 до 20 символов")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String nickname;

    @Schema(description = "Адрес электронной почты", example = "lezeks@gmail.com")
    @Size(min = 5, max = 50, message = "Адрес электронной почты должен содержать от 5 до 50 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "123456qwerty")
    @Size(max = 50, message = "Длина пароля должна быть не более 50 символов")
    private String password;
}
