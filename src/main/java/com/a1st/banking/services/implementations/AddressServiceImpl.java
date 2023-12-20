package com.a1st.banking.services.implementations;

import com.a1st.banking.Validators.ObjectsValidator;
import com.a1st.banking.dto.AddressDto;
import com.a1st.banking.models.Address;
import com.a1st.banking.repositories.AddressRepository;
import com.a1st.banking.services.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepo;
    private final ObjectsValidator<AddressDto> validator;
    @Override
    public Long save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return addressRepo.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepo.findAll().stream().map(AddressDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Long id) {
        return addressRepo.findById(id).map(AddressDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("No address found with the ID :"+ id));
    }

    @Override
    public void delete(Long id) {
        //todo check delete
        addressRepo.deleteById(id);

    }
}
