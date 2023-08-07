package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.config.AppConfig;
import africa.semicolon.promescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promescuous.dto.response.EmailNotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@AllArgsConstructor

public class BrevoMailService implements MailServices{

    private AppConfig appConfig;
    @Override
    public EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest) {
        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";

        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", appConfig.getMailConfig());
        HttpEntity<EmailNotificationRequest> request = new HttpEntity<>(emailNotificationRequest,headers);



        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<EmailNotificationResponse> response =
                restTemplate.postForEntity(brevoMailAddress,request,EmailNotificationResponse.class);
        EmailNotificationResponse emailNotificationResponse = response.getBody();
        return emailNotificationResponse;
    }
}
