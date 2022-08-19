package me.revilla.fullstackbp.controller;

import lombok.RequiredArgsConstructor;
import me.revilla.fullstackbp.dto.CustomerDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.dto.response.CustomerResponse;
import me.revilla.fullstackbp.service.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CustomerController
 *
 * @author Revilla Pool
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<?>> customers(
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "customerId", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        List<?> response = this.customerService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> customer(@PathVariable Long customerId) {
        CustomerDto response = this.customerService.findOne(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerDto data) {
        CustomerDto response = this.customerService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponse(response));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long customerId,
                                              @RequestBody CustomerDto data) {
        ApiResponse response = this.customerService.update(customerId, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long customerId) {
        ApiResponse response = this.customerService.delete(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
