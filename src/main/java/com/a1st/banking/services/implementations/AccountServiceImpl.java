package com.a1st.banking.services.implementations;

import com.a1st.banking.CustomException.OperationNonPermittedException;
import com.a1st.banking.Validators.ObjectsValidator;
import com.a1st.banking.dto.AccountDto;
import com.a1st.banking.models.Account;
import com.a1st.banking.repositories.AccountRepository;
import com.a1st.banking.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepo;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Long save(AccountDto dto) {
        /*
        // block account update, iban can't be changed.
        if(dto.getId() != null){
            throw new OperationNonPermittedException("Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted");
        }
        */
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepo.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount && account.getUser().isActive()) {
            throw new OperationNonPermittedException(
                    "the selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        // generate random IBAN when creating new account else do not update the IBAN
        if (dto.getId() == null) {
            account.setIban(generateRandomIban());
        }
        return accountRepo.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepo.findAll().stream().map(AccountDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Long id) {
        return accountRepo.findById(id).map(AccountDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("No account was found with ID: " + id));
    }

    @Override
    public void delete(Long id) {
        // todo check delete account
        accountRepo.deleteById(id);

    }

    private String generateRandomIban() {
        // generate an iban
        String iban = Iban.random(CountryCode.HU).toFormattedString();
        // check if iban already exists or not
        boolean ibanExists = accountRepo.findByIban(iban).isPresent();
        // if exists -> generate new random iban
        // if not exist -> return generated iban
        return ibanExists ? generateRandomIban() : iban;
    }
}
