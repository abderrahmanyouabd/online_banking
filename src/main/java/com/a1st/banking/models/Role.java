package com.a1st.banking.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Role extends AbstractEntity{


    private String name;

    @OneToMany
//    @JoinColumn(name = "user_id")
    private List<User> user;

}
