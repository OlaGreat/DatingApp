package africa.semicolon.promescuous.repository;

import africa.semicolon.promescuous.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
