package africa.semicolon.promiscuous.repository;

import africa.semicolon.promiscuous.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
