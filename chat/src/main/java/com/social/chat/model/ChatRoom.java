package com.social.chat.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collation = "chatroom")
public class ChatRoom {
    @Id
    private String id;

    private String chatId;

    private String senderId;

    private String recipientId;
}
