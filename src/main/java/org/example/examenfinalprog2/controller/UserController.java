package org.example.examenfinalprog2.controller;

import org.example.examenfinalprog2.model.CashFlow;
import org.example.examenfinalprog2.services.CashFlowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final CashFlowService cashFlowService;

    public UserController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }

    // GET /users/{id}/cash-flows -> donations + expenses made by that user
    @GetMapping("/users/{id}/cash-flows")
    public ResponseEntity<List<CashFlow>> getUserCashFlows(@PathVariable String id) {
        return ResponseEntity.ok(cashFlowService.findByUserId(id));
    }
}
