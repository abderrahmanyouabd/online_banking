package com.a1st.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
@author: Abderrahman Youabd aka: A1ST
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Contact extends AbstractEntity{


    private String firstName;
    private String lastName;
    private String email;
    private String iban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
