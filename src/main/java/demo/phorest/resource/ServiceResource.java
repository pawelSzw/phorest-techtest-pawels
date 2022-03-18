package demo.phorest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
public class ServiceResource implements Resource{

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @Size(max = 200)
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull
    @JsonProperty("loyalty_points")
    private Integer loyaltyPoints;

    @NotNull
    @JsonProperty("appointment_id")
    private UUID appointmentId;

}
