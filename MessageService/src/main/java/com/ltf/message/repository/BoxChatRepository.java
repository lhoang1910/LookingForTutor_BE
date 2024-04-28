package com.ltf.message.repository;

import com.ltf.message.entities.BoxChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxChatRepository extends JpaRepository<BoxChat, Long> {
}
