package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.dto.request.SendMessageRequest;
import africa.semicolon.promiscuous.dto.response.SentMessageResponse;
import africa.semicolon.promiscuous.models.Message;

import java.util.List;

public interface MessageService {
    SentMessageResponse sendMessage(SendMessageRequest request, long senderId, long receiverId);


    List<Message> getConversation(Long receiverId, long senderId);
}
