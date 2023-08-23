package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.NotificationRequest;
import africa.semicolon.promescuous.dto.response.GetUserResponse;
import africa.semicolon.promescuous.dto.response.NotificationResponse;
import africa.semicolon.promescuous.models.Notification;
import africa.semicolon.promescuous.models.User;
import africa.semicolon.promescuous.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static africa.semicolon.promescuous.utils.AppsUtil.NOTIFICATION_SENT;

@Service
@AllArgsConstructor
public class PromiscuousNotificationService implements NotificationService {

    NotificationRepository notificationRepository;
    UserServices userServices;
    @Override
    public NotificationResponse sendAppNotification(NotificationRequest request, long senderId, long receiverId) {
        Notification notification = new Notification();
        notification.setContent(request.getContent());
        notification.setSenderId(senderId);

        User foundUser = userServices.findUserById(receiverId);
        notification.setUser(foundUser);

        notificationRepository.save(notification);

        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setMessage(NOTIFICATION_SENT);


        return notificationResponse;
    }
}
