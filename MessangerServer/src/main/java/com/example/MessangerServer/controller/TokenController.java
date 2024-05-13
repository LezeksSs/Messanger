package com.example.MessangerServer.controller;

import com.example.MessangerServer.service.JwtService;
import com.example.MessangerServer.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class TokenController {
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        boolean isValid = jwtService.isTokenValid(token, userService.userDetailsService().loadUserByUsername(jwtService.extractUserName(token)));
        return ResponseEntity.ok(Collections.singletonMap("isValid", isValid));
    }

}
