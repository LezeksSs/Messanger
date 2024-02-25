package com.example.MessangerServer.controller;

import com.example.MessangerServer.dto.JwtAuthenticationResponse;
import com.example.MessangerServer.dto.SingUpRequest;
import com.example.MessangerServer.dto.SingInRequest;
import com.example.MessangerServer.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public JwtAuthenticationResponse singUp(@RequestBody @Valid SingUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SingInRequest request) {
        return authenticationService.signIn(request);
    }
}
