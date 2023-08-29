package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promiscuous.dto.response.EmailNotificationResponse;

public interface MailServices {
    EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest);
}
