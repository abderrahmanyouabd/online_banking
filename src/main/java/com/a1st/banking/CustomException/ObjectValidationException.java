package com.a1st.banking.CustomException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@Getter
@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException{

    private final Set<String> violations;
    private final String violationSource;


}
