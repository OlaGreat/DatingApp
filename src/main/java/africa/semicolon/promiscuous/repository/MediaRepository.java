package africa.semicolon.promiscuous.repository;

import africa.semicolon.promiscuous.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
