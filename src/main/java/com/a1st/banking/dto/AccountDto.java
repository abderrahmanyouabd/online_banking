package com.a1st.banking.dto;

import com.a1st.banking.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */


@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDto {

//    needed to make a user to have an account because not every user have an account we need to activate his account first.
    private Long id;

    private String iban;
    private UserDto user;

    public static AccountDto fromEntity(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.fromEntity(account.getUser()))
                .build();
    }

    public static Account toEntity(AccountDto accountDto){
        return Account.builder()
                .id(accountDto.getId())
                .iban(accountDto.getIban())
                .user(UserDto.toEntity(accountDto.getUser()))
                .build();
    }

}
