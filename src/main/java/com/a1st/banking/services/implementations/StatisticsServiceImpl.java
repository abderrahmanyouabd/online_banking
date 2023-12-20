package com.a1st.banking.services.implementations;

import com.a1st.banking.dto.TransactionSumDetails;
import com.a1st.banking.models.TransactionType;
import com.a1st.banking.repositories.TransactionRepository;
import com.a1st.banking.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final TransactionRepository transactionRepo;


    @Override
    public List<TransactionSumDetails> findSumTransactionsByDate(LocalDate startDate, LocalDate endDate, Long userId) {
        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
        return transactionRepo.findSumTransactionByDate(start, end, userId);
    }

    @Override
    public BigDecimal getAccountBalance(Long userId) {
        return transactionRepo.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Long userId) {
        return transactionRepo.findHighestAmountByTransType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Long userId) {
        return transactionRepo.findHighestAmountByTransType(userId, TransactionType.DEPOSIT);
    }

    @Override
    public BigDecimal highestWithdrawal(Long userId) {
        return transactionRepo.findHighestAmountByTransType(userId, TransactionType.WITHDRAWAL);
    }
}
