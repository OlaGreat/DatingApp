package africa.semicolon.promiscuous.repository;

import africa.semicolon.promiscuous.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderId(long senderId);

    List<Message> findByUserId(long receiverId);

}
