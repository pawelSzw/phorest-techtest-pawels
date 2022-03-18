package demo.phorest.repository;

import demo.phorest.enity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Client entity Repository
 */
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, UUID> {
}
