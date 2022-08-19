package me.revilla.fullstackbp.service;

import me.revilla.fullstackbp.dto.AccountDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;

/**
 * AccountService
 *
 * @author Revilla Pool
 */
public interface AccountService extends GeneralService<AccountDto, Long> {

    AccountDto create(AccountDto data);

    ApiResponse update(Long accountId, AccountDto data);

}
