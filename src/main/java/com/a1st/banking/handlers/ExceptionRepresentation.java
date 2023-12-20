package com.a1st.banking.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public class ExceptionRepresentation {

    private String errorMsg;
    private String errorSource;
    private Set<String> validationErrors;
}
