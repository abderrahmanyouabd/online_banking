package com.a1st.banking.dto;

import com.a1st.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull(message = "lastname should not be empty!")
    @NotEmpty(message = "lastname should not be empty!")
    @NotBlank(message = "lastname should not be empty!")
    private String lastName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
    private String role;
    private String iban;
    private boolean active;

//    @Past
//    private LocalDateTime birthdate;

//    GET
    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .iban(user.getAccount()== null ? "":user.getAccount().getIban())
                .active(user.isActive())
                .build();
    }

    //    POST
    public static User toEntity(UserDto user){
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
