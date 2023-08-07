package africa.semicolon.promescuous.services;

import africa.semicolon.promescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promescuous.dto.request.Recipient;
import africa.semicolon.promescuous.dto.request.Sender;
import africa.semicolon.promescuous.dto.response.EmailNotificationResponse;
import africa.semicolon.promescuous.service.MailServices;
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
        String recipientEmail = "oladipupoolamilekan2@gmail.com";
        String message = "<p>testing Mail Service</p>";
        String mailSender = "noreply@promiscuous.com";
        String subject ="test email";


        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        Recipient recipient = new Recipient();
        recipient.setEmail(recipientEmail);
        request.setRecipients(List.of(recipient));
        Sender sender = new Sender();
        sender.setEmail(mailSender);
        request.setSender(sender);
        request.setSubject(subject);

        EmailNotificationResponse emailNotificationResponse =mailServices.send(request);
        assertNotNull(emailNotificationResponse);


    }
}
