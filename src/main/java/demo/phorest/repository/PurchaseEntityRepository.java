package demo.phorest.repository;

import demo.phorest.enity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, UUID> {
}
