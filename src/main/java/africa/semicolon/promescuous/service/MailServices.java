package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promescuous.dto.response.EmailNotificationResponse;
import org.springframework.web.client.RestTemplate;

public interface MailServices {
    EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest);
}
