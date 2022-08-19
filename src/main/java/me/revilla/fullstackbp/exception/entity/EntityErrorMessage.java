package me.revilla.fullstackbp.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.revilla.fullstackbp.dto.response.ApiResponse;

import java.time.LocalDateTime;

/**
 * EntityErrorMessage
 *
 * @author Revilla Pool
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityErrorMessage {

    private Integer statusCode;
    private LocalDateTime timestamp;
    private ApiResponse response;
    private String description;

}
