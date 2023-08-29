package africa.semicolon.promiscuous.repository;

import africa.semicolon.promiscuous.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
