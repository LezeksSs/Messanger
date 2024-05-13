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
@Schema(description = "Текст сообщения")
public class MessageRequest {
    @Schema(description = "Текст сообщения", example = "Привет мир!")
    private String text;
}
