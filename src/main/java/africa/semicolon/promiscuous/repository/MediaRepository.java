package africa.semicolon.promiscuous.repository;

import africa.semicolon.promiscuous.models.Media;
import africa.semicolon.promiscuous.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Optional<Media> findByUser(User user);
}
