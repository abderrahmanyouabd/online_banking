package com.a1st.banking.services;

import com.a1st.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{
    List<ContactDto> findAllContactsByUserId(Long userId);
}

