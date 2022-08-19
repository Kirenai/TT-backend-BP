package me.revilla.fullstackbp.controller;

import lombok.RequiredArgsConstructor;
import me.revilla.fullstackbp.dto.MovementDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.dto.response.MovementResponse;
import me.revilla.fullstackbp.service.MovementService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MovementController
 *
 * @author Revilla Pool
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movements")
public class MovementController {

    private final MovementService movementService;

    @GetMapping
    public ResponseEntity<List<MovementDto>> customers(
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "movementId", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        List<MovementDto> response = this.movementService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<MovementResponse>> movementsByDateAndCustomer(@RequestParam("date") String date,
                                                                             @RequestParam("customerId") Long customerId) {
        List<MovementDto> response = this.movementService.findMovementsByDateAndCustomer(date, customerId);
        List<MovementResponse> responseList = response
                .stream()
                .map(MovementResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<MovementDto> movement(@PathVariable Long movementId) {
        MovementDto response = this.movementService.findOne(movementId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody MovementDto data) {
        ApiResponse response = this.movementService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{movementId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long movementId,
                                              @RequestBody MovementDto data) {
        ApiResponse response = this.movementService.update(movementId, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{movementId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long movementId) {
        ApiResponse response = this.movementService.delete(movementId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
