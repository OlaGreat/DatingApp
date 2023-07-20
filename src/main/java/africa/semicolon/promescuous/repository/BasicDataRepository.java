package africa.semicolon.promescuous.repository;

import africa.semicolon.promescuous.models.BasicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicDataRepository extends JpaRepository<BasicData, Long> {
}
