package com.example.MessangerServer.repository_dao;

import com.example.MessangerServer.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
