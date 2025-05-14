package com.rodolfocf.expenses.controller;

import com.rodolfocf.expenses.business.ExpenseService;
import com.rodolfocf.expenses.business.dto.ExpenseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.addExpense(expenseDTO));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> findExpensesByDate(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok(expenseService.findExpensesByDate(date));
    }

    @GetMapping("/sum")
    public ResponseEntity<BigDecimal> getDailyTotal(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok(expenseService.calculateDailyTotal(date));
    }

    @GetMapping("/between")
    public ResponseEntity<List<ExpenseDTO>> findExpensesBetween(@RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                                                @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        return ResponseEntity.ok(expenseService.findExpensesBetween(startDate, endDate));
    }

    @GetMapping("/sum_between")
    public ResponseEntity<BigDecimal> getTotalBetween(@RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                                      @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        return ResponseEntity.ok(expenseService.calculateTotalBetween(startDate, endDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable("id") Long id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok().body("Expense deleted.");
    }
}
