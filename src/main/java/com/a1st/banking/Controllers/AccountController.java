package com.a1st.banking.Controllers;

import com.a1st.banking.dto.AccountDto;
import com.a1st.banking.services.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/accounts")
@RestController
@RequiredArgsConstructor
@Tag(name = "accounts")
public class AccountController {

    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<Long> save(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(service.save(accountDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> findById(@PathVariable("account-id") long accountId){
        return ResponseEntity.ok(service.findById(accountId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(@PathVariable("account-id") Long accountId){
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
