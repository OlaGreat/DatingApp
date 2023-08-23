package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.SendMessageRequest;
import africa.semicolon.promescuous.dto.response.SentMessageResponse;

public interface MessageService {
    SentMessageResponse sendMessage(SendMessageRequest request, long senderId, long receiverId);

}
