package com.rodolfocf.expenses_api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfocf.expenses_api.business.ExpensesService;
import com.rodolfocf.expenses_api.business.dto.ExpensesRequestDTO;
import com.rodolfocf.expenses_api.business.dto.ExpensesResponseDTO;
import com.rodolfocf.expenses_api.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpensesController {

    private final ExpensesService expensesService;

    @PostMapping("/register")
    public ResponseEntity<ExpensesResponseDTO> registerExpenses(@RequestBody ExpensesRequestDTO expensesRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(expensesService.registerExpenses(expensesRequestDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExpensesResponseDTO>> getExpensesBetween(@RequestParam @JsonFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
                                                                        @RequestParam @JsonFormat(pattern = "dd/MM/yyyy") LocalDate endDate){
        return ResponseEntity.status(HttpStatus.OK).body(expensesService.getExpensesByDateRange(startDate, endDate));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpensesResponseDTO> updateExpenseById(@PathVariable("id") Long id,@RequestBody ExpensesRequestDTO expensesRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(expensesService.updateExpenseById(id, expensesRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable("id") Long id){
        try {
            expensesService.deleteExpenseById(id);
            return ResponseEntity.ok("Expense successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal error");
        }
    }
}
