package com.example.MessangerServer.repository_dao;

import com.example.MessangerServer.entity.Chat;
import com.example.MessangerServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    boolean existsByName(String name);

    Chat getChatByName(String name);
}
