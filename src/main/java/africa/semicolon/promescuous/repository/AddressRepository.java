package africa.semicolon.promescuous.repository;

import africa.semicolon.promescuous.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
