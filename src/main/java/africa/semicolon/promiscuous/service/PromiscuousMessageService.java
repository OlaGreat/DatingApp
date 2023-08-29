package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.dto.request.SendMessageRequest;
import africa.semicolon.promiscuous.dto.response.SentMessageResponse;
import africa.semicolon.promiscuous.models.Message;
import africa.semicolon.promiscuous.models.User;
import africa.semicolon.promiscuous.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

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

    @Override
    public List<Message> getConversation(Long receiverId, long senderId) {
        List<Message> allConversations = messageRepository.findAll();

        Predicate <Message> convo =
                conversations->conversations.getUser().getId().equals(receiverId) || conversations.getUser().getId().equals(senderId)
                || conversations.getSenderId().equals(senderId) || conversations.getSenderId().equals(receiverId);



       List<Message>chat = allConversations.stream()
                                           .filter(convo)
                                           .toList();


        return chat;
    }


}
