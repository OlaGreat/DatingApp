package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.config.AppConfig;
import africa.semicolon.promiscuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promiscuous.dto.response.EmailNotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor

public class BrevoMailService implements MailServices{

    //Todo: Remove hardCoded value
    private AppConfig appConfig;
    @Override
    public EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest) {
        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";

        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", appConfig.getMailConfig());
        headers.set("Content-Type", "application/json");
        HttpEntity<EmailNotificationRequest> request = new HttpEntity<>(emailNotificationRequest,headers);



        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<EmailNotificationResponse> response =
                restTemplate.postForEntity(brevoMailAddress,request,EmailNotificationResponse.class);
        EmailNotificationResponse emailNotificationResponse = response.getBody();
        return emailNotificationResponse;
    }
}
