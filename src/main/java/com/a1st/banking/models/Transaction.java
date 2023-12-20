package com.a1st.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Transaction extends AbstractEntity{


    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(updatable = false)
    private LocalDate transactionDate;

    private String destination_iban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
