package me.revilla.fullstackbp.service;

import me.revilla.fullstackbp.dto.CustomerDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;

/**
 * CustomerService
 *
 * @author Revilla Pool
 */
public interface CustomerService extends GeneralService<CustomerDto, Long> {

    CustomerDto create(CustomerDto data);

    ApiResponse update(Long customerId, CustomerDto data);

}
