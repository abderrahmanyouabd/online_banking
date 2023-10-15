package com.a1st.banking.dto;


import com.a1st.banking.models.Contact;
import com.a1st.banking.models.User;
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
public class ContactDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    private Long userId;

    public static ContactDto fromEntity(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();

    }

    public static Contact toEntity(ContactDto contact){
        return Contact.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(User.builder().id(contact.getUserId()).build()
                )
                .build();

    }



}
