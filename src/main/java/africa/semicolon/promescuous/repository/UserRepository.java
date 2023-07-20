package africa.semicolon.promescuous.repository;

import africa.semicolon.promescuous.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
