package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.NotificationRequest;
import africa.semicolon.promescuous.dto.response.NotificationResponse;


public interface NotificationService {
    NotificationResponse sendAppNotification(NotificationRequest request, long senderId, long receiverId);
}
