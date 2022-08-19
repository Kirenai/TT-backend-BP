package me.revilla.fullstackbp.service;

import me.revilla.fullstackbp.dto.MovementDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;

import java.util.List;

/**
 * MovementService
 *
 * @author Revilla Pool
 */
public interface MovementService extends GeneralService<MovementDto, Long> {

    ApiResponse create(MovementDto data);

    ApiResponse update(Long movementId, MovementDto data);

    List<MovementDto> findMovementsByDateAndCustomer(String date, Long customerId);

}
