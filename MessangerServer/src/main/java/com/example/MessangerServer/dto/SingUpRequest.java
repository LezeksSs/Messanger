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
    @Size(min = 2, max = 20, message = "Username must be from 2 to 20 characters")
    @NotBlank(message = "Username field can not be blank")
    private String nickname;

    @Schema(description = "Адрес электронной почты", example = "lezeks@gmail.com")
    @Size(min = 5, max = 50, message = "Email address field must be from 5 to 50 characters")
    @NotBlank(message = "Email field can not be blank")
    @Email(message = "Email address must be in following format: user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "123456qwerty")
    @Size(max = 50, message = "Length of the password should not exceed 50 letters")
    private String password;
}
