package demo.phorest.enity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Service Entity
 */
@Entity(name = "Service")
@Table(name = "TSERVICE")
@Getter
@Setter
public class ServiceEntity extends PurchaseServiceBaseEntity {

}
