package com.a1st.banking.Controllers;

import com.a1st.banking.dto.TransactionSumDetails;
import com.a1st.banking.services.StatisticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/statistics")
@RestController
@RequiredArgsConstructor
@Tag(name = "statistics")
public class StatisticsController {

    private final StatisticsService service;


    @GetMapping("/sum-by-date/{user-id}")
    public ResponseEntity<List<TransactionSumDetails>> findSumTransactionsByDate(
            @PathVariable("user-id") Long userId,
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return ResponseEntity.ok(service.findSumTransactionsByDate(startDate, endDate, userId));
    }

    @GetMapping("/account-balance/{user-id}")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(service.getAccountBalance(userId));
    }

    @GetMapping("/highest-transfer/{user-id}")
    public ResponseEntity<BigDecimal> highestTransfert(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(service.highestTransfert(userId));
    }

    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(service.highestDeposit(userId));
    }

    @GetMapping("/highest-withdraw/{user-id}")
    public ResponseEntity<BigDecimal> highestWithdrawal(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(service.highestWithdrawal(userId));
    }

}
