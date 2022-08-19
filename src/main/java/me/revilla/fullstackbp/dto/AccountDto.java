package me.revilla.fullstackbp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AccountDto
 *
 * @author Revilla Pool
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {

    private Long accountId;
    private String accountNumber;
    private String accountType;
    private Integer initialBalance;
    private Boolean state;
    private CustomerDto customer;

}
