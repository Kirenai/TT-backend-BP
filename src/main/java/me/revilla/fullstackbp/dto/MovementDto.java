package me.revilla.fullstackbp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * MovementDto
 *
 * @author Revilla Pool
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovementDto {

    private Long movementId;
    private LocalDate date;
    private String movementType;
    private Integer value;
    private Integer balance;
    private AccountDto account;

}
