package com.social.chat.service.impl;

import com.social.chat.model.ChatMessage;
import com.social.chat.repository.ChatMessageRepository;
import com.social.chat.service.IChatMessageService;
import com.social.chat.service.IChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final IChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
