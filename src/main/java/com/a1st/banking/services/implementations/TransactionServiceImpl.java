package com.a1st.banking.services.implementations;

import com.a1st.banking.Validators.ObjectsValidator;
import com.a1st.banking.dto.TransactionDto;
import com.a1st.banking.models.Transaction;
import com.a1st.banking.models.TransactionType;
import com.a1st.banking.repositories.TransactionRepository;
import com.a1st.banking.services.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;
    private final ObjectsValidator<TransactionDto> validator;
    @Override
    public Long save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal amount = transaction.getAmount().multiply(BigDecimal.valueOf(transactionType(transaction.getType())));
        transaction.setAmount(amount);
        return transactionRepo.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepo.findAll().stream().map(TransactionDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Long id) {
        return transactionRepo.findById(id).map(TransactionDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("Transaction not found with ID: " + id));
    }

    @Override
    public void delete(Long id) {
        // todo check delete transaction
        transactionRepo.deleteById(id);
    }

    private int transactionType(TransactionType type){
        return TransactionType.DEPOSIT == type ? 1:-1;
    }


    @Override
    public List<TransactionDto> findAllTransactionsById(Long userId) {
        return transactionRepo.findAllByUserId(userId).stream()
                .map(TransactionDto::fromEntity).collect(Collectors.toList());
    }
}
