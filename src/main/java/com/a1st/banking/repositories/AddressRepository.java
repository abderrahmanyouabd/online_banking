package com.a1st.banking.repositories;

import com.a1st.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
