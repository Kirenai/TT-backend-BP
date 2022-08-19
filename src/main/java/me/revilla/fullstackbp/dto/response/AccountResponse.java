package me.revilla.fullstackbp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.revilla.fullstackbp.dto.AccountDto;

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
public class AccountResponse {

    private String accountNumber;
    private String type;
    private Integer initialBalance;
    private Boolean state;
    private String customer;

    public AccountResponse(AccountDto accountDto) {
        this.accountNumber = accountDto.getAccountNumber();
        this.type = accountDto.getAccountType();
        this.initialBalance = accountDto.getInitialBalance();
        this.state = accountDto.getState();
        this.customer = accountDto.getCustomer().getPerson().getName();
    }

}
