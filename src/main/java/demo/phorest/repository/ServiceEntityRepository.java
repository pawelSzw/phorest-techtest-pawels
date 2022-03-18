package demo.phorest.repository;

import demo.phorest.enity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, UUID> {
}
