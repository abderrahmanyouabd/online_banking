package com.a1st.banking.services.implementations;

import com.a1st.banking.Validators.ObjectsValidator;
import com.a1st.banking.dto.ContactDto;
import com.a1st.banking.models.Contact;
import com.a1st.banking.repositories.ContactRepository;
import com.a1st.banking.services.ContactService;
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
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepo;
    private final ObjectsValidator<ContactDto> validator;
    @Override
    public Long save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return contactRepo.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepo.findAll().stream().map(ContactDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Long id) {
        return contactRepo.findById(id).map(ContactDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("No contact was found with ID: " + id));
    }

    @Override
    public void delete(Long id) {
        // todo check delete
        contactRepo.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllContactsByUserId(Long userId) {
        return contactRepo.findAllContactsByUserId(userId)
                .stream().map(ContactDto::fromEntity).collect(Collectors.toList());
    }
}
