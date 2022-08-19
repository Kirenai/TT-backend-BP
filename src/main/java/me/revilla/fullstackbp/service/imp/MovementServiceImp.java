package me.revilla.fullstackbp.service.imp;

import lombok.RequiredArgsConstructor;
import me.revilla.fullstackbp.dto.MovementDto;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.entity.Account;
import me.revilla.fullstackbp.entity.Customer;
import me.revilla.fullstackbp.entity.Movement;
import me.revilla.fullstackbp.exception.entity.EntityNoSuchElementException;
import me.revilla.fullstackbp.repository.AccountRepository;
import me.revilla.fullstackbp.repository.MovementRepository;
import me.revilla.fullstackbp.service.MovementService;
import me.revilla.fullstackbp.util.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MovementServiceImp
 *
 * @author Revilla Pool
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MovementServiceImp extends GeneralServiceImp<MovementDto, Long, Movement> implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    @Override
    public JpaRepository<Movement, Long> getRepo() {
        return this.movementRepository;
    }

    @Override
    public List<MovementDto> findMovementsByDateAndCustomer(String date, Long customerId) {
        String[] dateSplit = date.split(" ");
        return this.movementRepository.findByDateBetweenAndAccount_Customer_CustomerId(
                        LocalDate.parse(dateSplit[0]),
                        LocalDate.parse(dateSplit[1]),
                        customerId
                )
                .stream().map(movement -> super.getModelMapper().map(movement, super.getFirstGenericClass()))
                .collect(Collectors.toList());
    }

    @Override
    public ApiResponse create(MovementDto movementDto) {
        Account account = Entity.getById(movementDto.getAccount().getAccountId(), this.accountRepository, Account.class);
        Movement movement = super.getModelMapper().map(movementDto, super.getThirdGenericClass());
        movement.setDate(LocalDate.now());

        if (movement.getMovementType().equals("RETIRO")) {
            if (account.getAccountType().equals("Ahorros") && account.getInitialBalance().equals(0)) {
                return new ApiResponse(Boolean.FALSE, "Balance not available");
            }
            int amountWithdrawal = account.getInitialBalance() - movement.getValue();
            movement.setBalance(amountWithdrawal);
            movement.setValue(movement.getValue() * -1);
        }

        if (movement.getMovementType().equals("DEPOSITO")) {
            int amountDeposit = account.getInitialBalance() + movement.getValue();
            movement.setBalance(amountDeposit);
        }
        this.movementRepository.save(movement);
        return new ApiResponse(Boolean.TRUE, "Movement created successfully");
    }

    @Override
    public ApiResponse update(Long movementId, MovementDto movementDto) {
        return this.movementRepository.findById(movementId)
                .map(movement -> {
                    movement.setDate(movementDto.getDate());
                    movement.setMovementType(movementDto.getMovementType());
                    movement.setBalance(movementDto.getBalance());
                    return new ApiResponse(Boolean.TRUE, "Movement updated successfully");
                })
                .orElseThrow(() -> new EntityNoSuchElementException(
                        Customer.class.getSimpleName()
                                + " not found with id: " + movementId
                ));
    }

}
