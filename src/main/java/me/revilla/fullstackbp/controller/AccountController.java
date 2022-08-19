package me.revilla.fullstackbp.controller;

import lombok.RequiredArgsConstructor;
import me.revilla.fullstackbp.dto.AccountDto;
import me.revilla.fullstackbp.dto.response.AccountResponse;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.service.AccountService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AccountController
 *
 * @author Revilla Pool
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> accounts(
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "accountId", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        List<AccountDto> response = this.accountService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> account(@PathVariable Long accountId) {
        AccountDto response = this.accountService.findOne(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountDto data) {
        AccountDto response = this.accountService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountResponse(response));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long accountId,
                                              @RequestBody AccountDto data) {
        ApiResponse response = this.accountService.update(accountId, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long accountId) {
        ApiResponse response = this.accountService.delete(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
