package com.a1st.banking.repositories;

import com.a1st.banking.dto.TransactionDto;
import com.a1st.banking.dto.TransactionSumDetails;
import com.a1st.banking.models.Transaction;
import com.a1st.banking.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserId(Long userId);

    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Long userId);

    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransType(@Param("userId") Long userId, @Param("transactionType") TransactionType transactionType);

    @Query("select t.transactionDate as transactionDate, sum(t.amount) as amount from Transaction t where t.user.id = :userId and t.createdDate between :start and :end group by t.transactionDate")
    List<TransactionSumDetails> findSumTransactionByDate(LocalDateTime start, LocalDateTime end, Long userId);
}
