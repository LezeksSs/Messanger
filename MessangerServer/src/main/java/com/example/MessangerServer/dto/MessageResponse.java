package com.example.MessangerServer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с сообщением")
public class MessageResponse {

    private long id;

    private String text;

    private UserResponse user;

    private Date sending_date;

    private boolean sending_status;

    private boolean read_status;
}
