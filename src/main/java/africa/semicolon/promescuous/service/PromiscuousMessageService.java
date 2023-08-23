package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.SendMessageRequest;
import africa.semicolon.promescuous.dto.response.SentMessageResponse;
import africa.semicolon.promescuous.models.Message;
import africa.semicolon.promescuous.models.User;
import africa.semicolon.promescuous.repository.MessageRepository;
import africa.semicolon.promescuous.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PromiscuousMessageService implements MessageService{

    private final MessageRepository messageRepository;
    private final UserServices userServices;
    @Override
    public SentMessageResponse sendMessage(SendMessageRequest request, long senderId, long receiverId) {
        String messageContent = request.getMessageContent();

        Message message = new Message();

        message.setMessageBody(messageContent);
        message.setSenderId(senderId);

        User foundUser = userServices.findUserById(receiverId);
        message.setUser(foundUser);

        messageRepository.save(message);

        SentMessageResponse sentMessageResponse = new SentMessageResponse();
        sentMessageResponse.setMessage(messageContent);

        return sentMessageResponse;
    }
}
