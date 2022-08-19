package me.revilla.fullstackbp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.revilla.fullstackbp.dto.CustomerDto;

/**
 * CustomerResponse
 *
 * @author Revilla Pool
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResponse {

    private String name;
    private String address;
    private String phone;
    private String password;
    private Boolean state;

    public CustomerResponse(CustomerDto customerDto) {
        this.name = customerDto.getPerson().getName();
        this.address = customerDto.getPerson().getAddress();
        this.phone = customerDto.getPerson().getPhone();
        this.password = customerDto.getPassword();
        this.state = customerDto.getState();
    }

}
