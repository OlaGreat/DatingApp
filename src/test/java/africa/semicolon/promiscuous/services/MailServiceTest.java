package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promiscuous.dto.request.Recipient;
import africa.semicolon.promiscuous.dto.response.EmailNotificationResponse;
import africa.semicolon.promiscuous.service.MailServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailServices mailServices;

    @Test
    public void testThatEmailServiceWorks(){
        String recipientEmail = "rixode7827@v1zw.com";
        String message = "<p>testing Mail Service</p>";
        String mailSender = "noreply@promiscuous.com";
        String subject ="test email";


        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        request.setRecipients(List.of(new Recipient(recipientEmail)));
        request.setSubject(subject);

        EmailNotificationResponse emailNotificationResponse =mailServices.send(request);
        assertNotNull(emailNotificationResponse.toString());
        System.out.println("--->{}  " + emailNotificationResponse);


    }
}
