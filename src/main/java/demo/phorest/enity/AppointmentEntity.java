package demo.phorest.enity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Appointment Entity
 */
@Entity(name = "Appointment")
@Table(name = "TAPPOINTMENT")
@Getter
@Setter
public class AppointmentEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "start_time")
    private Date  startTime;

    @Column(name = "end_time")
    private Date endTime;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientEntity client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="appointment", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<PurchaseEntity> purchases;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="appointment", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<ServiceEntity> services;
}
