package com.a1st.banking.dto;

import com.a1st.banking.models.Address;
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
public class AddressDto  {

    private Long id;
    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String country;
    //    needed to modify the address of a user.
    private Long userId;


    public static AddressDto fromEntity(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .userId(address.getUser().getId())
                .build();

    }

    public static Address toEntity(AddressDto address){
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .user(User.builder().id(address.getUserId()).build())
                .build();

    }



}

