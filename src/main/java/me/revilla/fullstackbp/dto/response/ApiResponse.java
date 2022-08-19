package me.revilla.fullstackbp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiResponseDto
 *
 * @author Revilla Pool
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private Boolean success;
    private String message;
}
