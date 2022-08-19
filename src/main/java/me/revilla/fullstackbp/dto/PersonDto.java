package me.revilla.fullstackbp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PersonDto
 *
 * @author Revilla Pool
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long personId;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;

}
