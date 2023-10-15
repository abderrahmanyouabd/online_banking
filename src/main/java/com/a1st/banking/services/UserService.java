package com.a1st.banking.services;

import com.a1st.banking.dto.AuthenticationResponse;
import com.a1st.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{


    Long validateAccount(Long id);
    Long invalidateAccount(Long id);

    AuthenticationResponse register(UserDto user);
}
