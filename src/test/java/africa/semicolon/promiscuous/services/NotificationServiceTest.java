package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dto.request.NotificationRequest;
import africa.semicolon.promiscuous.dto.response.NotificationResponse;
import africa.semicolon.promiscuous.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = {"/db/insert.sql"})
public class NotificationServiceTest {
    @Autowired
    NotificationService notificationService;

    @Test
    void testThatAppNotificationCanBeSend(){
        NotificationRequest request = new NotificationRequest();
        request.setContent("Thank you for joining our platform we look forward to serving you better");
        NotificationResponse notificationResponse = notificationService.sendAppNotification(request,100L, 101L);
        assertThat(notificationResponse).isNotNull();
    }


//    @Test
//    void testEmailNotificationCanBeSend(){
//        NotificationRequest request = new NotificationRequest();
//        request.setContent("Thank you for joining our platform we look forward to serving you better");
//        NotificationResponse notificationResponse = notificationService.sendAppNotification(request,100L, 101L);
//        assertThat(notificationResponse).isNotNull();
//    }
}
