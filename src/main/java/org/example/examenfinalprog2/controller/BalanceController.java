package org.example.examenfinalprog2.controller;

import org.example.examenfinalprog2.services.CashFlowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BalanceController {

    private final CashFlowService cashFlowService;

    public BalanceController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }

    // GET /balance -> total donations minus total expenses
    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        return ResponseEntity.ok(cashFlowService.getBalance());
    }
}
