package demo.phorest.enity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


/**
 * Purchase Entity
 */
@Entity(name = "Purchase")
@Table(name = "TPURCHASE")
@Getter
@Setter
public class PurchaseEntity extends PurchaseServiceBaseEntity{

}
