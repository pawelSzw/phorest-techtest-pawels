package demo.phorest.enity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * Client Entity
 */
@Getter
@Setter
@MappedSuperclass
public abstract class PurchaseServiceBaseEntity {

    @Id
    @Column(name = "id")
    @NotNull
    private UUID id;

    @Size(max = 200)
    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "loyalty_points")
    @NotNull
    private Integer loyaltyPoints;

    @JoinColumn(name = "appointment_id")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private AppointmentEntity appointment;
}
