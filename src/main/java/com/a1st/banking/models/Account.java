package com.a1st.banking.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */




@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends AbstractEntity{

    private String iban;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
