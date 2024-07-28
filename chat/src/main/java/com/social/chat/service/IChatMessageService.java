package com.social.chat.service;

import com.social.chat.model.ChatMessage;

import java.util.List;

public interface IChatMessageService {
    ChatMessage save(ChatMessage chatMessage);

    List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
