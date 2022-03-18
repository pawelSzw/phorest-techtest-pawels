package demo.phorest.enity;

import demo.phorest.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * Client Entity
 */
@Entity(name = "Client")
@Table(name = "TCLIENT")
@Getter
@Setter
public class ClientEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Size(max = 200)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 200)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @Enumerated
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "banned")
    private Boolean banned;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<AppointmentEntity> appointments;
}

