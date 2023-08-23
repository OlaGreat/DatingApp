package africa.semicolon.promescuous.services;

import africa.semicolon.promescuous.service.MessageService;
import africa.semicolon.promescuous.dto.request.SendMessageRequest;
import africa.semicolon.promescuous.dto.response.SentMessageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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


}
