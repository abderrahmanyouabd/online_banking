package com.a1st.banking.dto;

import lombok.Data;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
