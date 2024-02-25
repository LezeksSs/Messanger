package com.example.MessangerServer.repository_dao;

import com.example.MessangerServer.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
