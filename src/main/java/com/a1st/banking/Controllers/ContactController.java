package com.a1st.banking.Controllers;

import com.a1st.banking.dto.ContactDto;
import com.a1st.banking.services.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/contacts")
@RestController
@RequiredArgsConstructor
@Tag(name = "contact")
public class ContactController {

    private final ContactService service;

    @PostMapping("/")
    public ResponseEntity<Long> save(@RequestBody ContactDto contactDto){
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contact-id") long contactId){
        return ResponseEntity.ok(service.findById(contactId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<ContactDto>> findAllContactsByUserId(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(service.findAllContactsByUserId(userId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(@PathVariable("contact-id") Long contactId){
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }
}
