package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promescuous.dto.response.EmailNotificationResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class BrevoMailService implements MailServices{
    @Override
    public EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest) {
        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";

        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "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");
        HttpEntity<EmailNotificationRequest> request = new HttpEntity<>(emailNotificationRequest,headers);



        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<EmailNotificationResponse> response =
                restTemplate.postForEntity(brevoMailAddress,request,EmailNotificationResponse.class);
        EmailNotificationResponse emailNotificationResponse = response.getBody();
        return emailNotificationResponse;
    }
}
