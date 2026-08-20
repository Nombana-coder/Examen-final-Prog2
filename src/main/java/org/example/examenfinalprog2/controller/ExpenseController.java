package org.example.examenfinalprog2.controller;

import org.example.examenfinalprog2.dto.CreateExpenseRequest;
import org.example.examenfinalprog2.model.Expense;
import org.example.examenfinalprog2.services.CashFlowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    private final CashFlowService cashFlowService;

    public ExpenseController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }

    // POST /expenses -> creates an expense and returns it (201 Created)
    @PostMapping("/expenses")
    public ResponseEntity<Expense> createExpense(@RequestBody CreateExpenseRequest request) {
        Expense created = cashFlowService.createExpense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
