package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.dto.request.NotificationRequest;
import africa.semicolon.promiscuous.dto.response.NotificationResponse;


public interface NotificationService {
    NotificationResponse sendAppNotification(NotificationRequest request, long senderId, long receiverId);
}
