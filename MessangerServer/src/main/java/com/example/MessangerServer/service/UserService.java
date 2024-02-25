package com.example.MessangerServer.service;

import com.example.MessangerServer.entity.User;
import com.example.MessangerServer.repository_dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(User user) {
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new RuntimeException("Пользователь с таким именем уже существуем");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существуем");
        }

        return saveUser(user);
    }

    public User getByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByNickname(nickname);
    }

    /**
     * Нужен для Spring Security
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByNickname;
    }
}
