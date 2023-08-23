package africa.semicolon.promescuous.repository;

import africa.semicolon.promescuous.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
