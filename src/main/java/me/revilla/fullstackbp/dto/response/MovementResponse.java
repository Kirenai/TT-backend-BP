package me.revilla.fullstackbp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.revilla.fullstackbp.dto.MovementDto;

import java.time.LocalDate;

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
public class MovementResponse {

    private LocalDate date;
    private String client;
    private String accountNumber;
    private String type;
    private Integer initialBalance;
    private Boolean state;
    private Integer movement;
    private Integer balance;


    public MovementResponse(MovementDto movementDto) {
        this.date = movementDto.getDate();
        this.client = movementDto.getAccount().getCustomer().getPerson().getName();
        this.accountNumber = movementDto.getAccount().getAccountNumber();
        this.type = movementDto.getMovementType();
        this.initialBalance = movementDto.getAccount().getInitialBalance();
        this.state = movementDto.getAccount().getState();
        this.movement = movementDto.getValue();
        this.balance = movementDto.getBalance();
    }

}
