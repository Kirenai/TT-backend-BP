package me.revilla.fullstackbp.service.imp;

import lombok.RequiredArgsConstructor;
import me.revilla.fullstackbp.dto.AccountDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.entity.Account;
import me.revilla.fullstackbp.entity.Customer;
import me.revilla.fullstackbp.exception.entity.EntityNoSuchElementException;
import me.revilla.fullstackbp.repository.AccountRepository;
import me.revilla.fullstackbp.repository.CustomerRepository;
import me.revilla.fullstackbp.service.AccountService;
import me.revilla.fullstackbp.util.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountServiceImp
 *
 * @author Revilla Pool
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImp extends GeneralServiceImp<AccountDto, Long, Account> implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public JpaRepository<Account, Long> getRepo() {
        return this.accountRepository;
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        Customer customer = Entity.getById(accountDto.getCustomer().getCustomerId(), this.customerRepository, Customer.class);
        Account account = super.getModelMapper().map(accountDto, super.getThirdGenericClass());
        account.setCustomer(customer);
        Account accountSaved = this.accountRepository.save(account);
        return super.getModelMapper().map(accountSaved, super.getFirstGenericClass());
    }

    @Override
    public ApiResponse update(Long accountId, AccountDto accountDto) {
        return this.accountRepository.findById(accountId)
                .map(account -> {
                    account.setAccountNumber(accountDto.getAccountNumber());
                    account.setAccountType(accountDto.getAccountType());
                    account.setInitialBalance(accountDto.getInitialBalance());
                    account.setState(accountDto.getState());
                    return new ApiResponse(Boolean.TRUE, "Account updated successfully");
                })
                .orElseThrow(() -> new EntityNoSuchElementException(
                        Account.class.getSimpleName()
                                + " not found with id: " + accountId
                ));
    }


}
