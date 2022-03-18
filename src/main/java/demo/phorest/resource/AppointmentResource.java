package demo.phorest.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class AppointmentResource implements Resource{

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("start_time")
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ZZZ")
    private Date startTime;

    @NotNull
    @JsonProperty("end_time")
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ZZZ")
    private Date endTime;

    @NotNull
    @JsonProperty("client_id")
    private UUID clientId;
}
