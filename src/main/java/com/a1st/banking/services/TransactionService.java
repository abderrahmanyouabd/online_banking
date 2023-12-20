package com.a1st.banking.services;

import com.a1st.banking.dto.TransactionDto;

import java.util.List;


public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllTransactionsById(Long userId);
}
