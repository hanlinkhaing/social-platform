package com.social.chat.service;

import java.util.Optional;

public interface IChatRoomService {
    Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists);

    String createChatRoomId(String senderId, String recipientId);
}
