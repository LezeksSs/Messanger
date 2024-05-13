package com.example.MessangerServer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с пользователем")
public class UserResponse {
    @Schema(description = "id пользователя", example = "1")
    private long id;
    @Schema(description = "Ник пользователя", example = "Lezeks")
    private String nickname;
    @Schema(description = "Email пользователя", example = "lezeks@gmail.com")
    private String email;
}
