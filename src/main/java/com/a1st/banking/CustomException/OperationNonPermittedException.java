package com.a1st.banking.CustomException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@Getter
@RequiredArgsConstructor
public class OperationNonPermittedException extends RuntimeException{

    private final String errorMsg;
    private  final String operationId;
    private final String source;
    private final String dependency;

}
