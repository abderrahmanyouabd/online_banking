package com.a1st.banking.services;

import com.a1st.banking.dto.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface StatisticsService {

    List<TransactionSumDetails> findSumTransactionsByDate(LocalDate startDate, LocalDate endDate, Long userId);
    BigDecimal getAccountBalance(Long userId);
    BigDecimal highestTransfert(Long userId);
    BigDecimal highestDeposit(Long userId);
    BigDecimal highestWithdrawal(Long userId);

}
