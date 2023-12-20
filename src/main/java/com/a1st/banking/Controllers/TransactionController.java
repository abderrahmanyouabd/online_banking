package com.a1st.banking.Controllers;

import com.a1st.banking.dto.TransactionDto;
import com.a1st.banking.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})@RequestMapping("/transactions")
@RestController
@RequiredArgsConstructor
@Tag(name = "transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Long> save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(service.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction-id") long transactionId){
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<List<TransactionDto>> findAllTransactionsById(@PathVariable("user_id") Long userId){
        return ResponseEntity.ok(service.findAllTransactionsById(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(@PathVariable("transaction-id") Long transactionId){
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}
