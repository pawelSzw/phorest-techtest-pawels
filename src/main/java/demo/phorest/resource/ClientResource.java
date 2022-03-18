package demo.phorest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.phorest.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Client Resource
 */
@Getter
@Setter
public class ClientResource implements Resource{

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @Size(max = 200)
    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @Size(max = 200)
    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @Size(max = 200)
    @NotNull
    @JsonProperty("email")
    private String email;

    @Size(max = 200)
    @NotNull
    @JsonProperty("phone")
    private String phoneNumber;

    @NotNull
    @JsonProperty("gender")
    private GenderEnum gender;

    @NotNull
    @JsonProperty("banned")
    private Boolean banned;

}

