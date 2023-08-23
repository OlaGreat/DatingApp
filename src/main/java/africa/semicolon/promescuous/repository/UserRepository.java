package africa.semicolon.promescuous.repository;

import africa.semicolon.promescuous.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> readByEmail(String email);
}
