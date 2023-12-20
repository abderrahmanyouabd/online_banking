package com.a1st.banking.dto;

import com.a1st.banking.models.Transaction;
import com.a1st.banking.models.TransactionType;
import com.a1st.banking.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Long id;
    @Positive
    private BigDecimal amount;

    private TransactionType type;

    private String destination_iban;
    private Long userId;// this ID is the id of the sender not receiver, gonna be the same in case sending money to himself

    private LocalDate transactionDate;


    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(transaction.getTransactionDate())
                .destination_iban(transaction.getDestination_iban())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDto transaction){
        return Transaction.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(LocalDate.now())
                .destination_iban(transaction.getDestination_iban())
                .user(User.builder().id(transaction.getUserId()).build())
                .build();
    }
}
