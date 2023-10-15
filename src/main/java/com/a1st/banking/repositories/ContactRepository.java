package com.a1st.banking.repositories;

import com.a1st.banking.dto.ContactDto;
import com.a1st.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllContactsByUserId(Long userId);
}
