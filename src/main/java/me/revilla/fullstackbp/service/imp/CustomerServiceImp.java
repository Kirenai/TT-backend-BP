package me.revilla.fullstackbp.service.imp;

import lombok.RequiredArgsConstructor;
import me.revilla.fullstackbp.dto.CustomerDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.entity.Customer;
import me.revilla.fullstackbp.exception.entity.EntityNoSuchElementException;
import me.revilla.fullstackbp.repository.CustomerRepository;
import me.revilla.fullstackbp.service.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomerServiceImp
 *
 * @author Revilla Pool
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImp extends GeneralServiceImp<CustomerDto, Long, Customer> implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public JpaRepository<Customer, Long> getRepo() {
        return this.customerRepository;
    }

    @Override
    public CustomerDto create(CustomerDto data) {
        Customer customer = super.getModelMapper().map(data, super.getThirdGenericClass());
        Customer customerSaved = this.customerRepository.save(customer);
        return super.getModelMapper().map(customerSaved, super.getFirstGenericClass());
    }

    @Override
    public ApiResponse update(Long customerId, CustomerDto customerDto) {
        return this.customerRepository.findById(customerId)
                .map(customer -> {
                    customer.setPassword(customerDto.getPassword());
                    customer.setState(customerDto.getState());
                    customer.getPerson().setName(customerDto.getPerson().getName());
                    customer.getPerson().setGender(customerDto.getPerson().getGender());
                    customer.getPerson().setAge(customerDto.getPerson().getAge());
                    customer.getPerson().setIdentification(customerDto.getPerson().getIdentification());
                    customer.getPerson().setAddress(customerDto.getPerson().getAddress());
                    customer.getPerson().setPhone(customerDto.getPerson().getPhone());
                    return new ApiResponse(Boolean.TRUE, "Customer updated successfully");
                })
                .orElseThrow(() -> new EntityNoSuchElementException(
                        Customer.class.getSimpleName()
                                + " not found with id: " + customerId
                ));
    }


}
