package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.models.Message;
import africa.semicolon.promiscuous.service.MessageService;
import africa.semicolon.promiscuous.dto.request.SendMessageRequest;
import africa.semicolon.promiscuous.dto.response.SentMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Test
    void testThatMessageCanBeSent(){
        SendMessageRequest request = new SendMessageRequest();
        request.setMessageContent("Hello there");

        SentMessageResponse response = messageService.sendMessage(request, 100L, 101L);
        assertThat(response).isNotNull();
    }

    @Test
    void testFetchAllMessagesBetweenTwoPeople(){
        SendMessageRequest request = new SendMessageRequest();
        request.setMessageContent("Hello there");
        SentMessageResponse response = messageService.sendMessage(request, 100L, 101L);

        SendMessageRequest request1 = new SendMessageRequest();
        request1.setMessageContent("Hello i am  good");
        SentMessageResponse response1 = messageService.sendMessage(request1, 100L, 101L);

        SendMessageRequest request2 = new SendMessageRequest();
        request2.setMessageContent("Hello there");
        SentMessageResponse response3 = messageService.sendMessage(request2, 100L, 101L);

        SendMessageRequest request11 = new SendMessageRequest();
        request11.setMessageContent("Hello i am  good");
        SentMessageResponse response11 = messageService.sendMessage(request11, 100L, 101L);



        List<Message> conversation = messageService.getConversation(100L, 101L);

        log.info("Conversation --->>>>" + conversation);



    }


}
