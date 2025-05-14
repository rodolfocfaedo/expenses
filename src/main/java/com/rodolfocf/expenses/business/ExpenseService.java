package com.rodolfocf.expenses.business;

import com.rodolfocf.expenses.business.dto.ExpenseDTO;
import com.rodolfocf.expenses.business.mapper.IExpenseMapper;
import com.rodolfocf.expenses.infrastructure.entities.Expense;
import com.rodolfocf.expenses.infrastructure.enums.ExpenseCategory;
import com.rodolfocf.expenses.infrastructure.repository.IExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final IExpenseRepository expenseRepository;
    private final IExpenseMapper expenseMapper;

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseMapper.toEntity(expenseDTO);
        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.toDTO(savedExpense);
    }

    public List<ExpenseDTO> findExpensesByDate(LocalDate localDate){
        List<Expense> expenses = expenseRepository.findExpensesByDate(localDate);
        return expenses.stream().map(expenseMapper :: toDTO).toList();
    }

    public BigDecimal calculateDailyTotal(LocalDate date) {
        List<Expense> expenses = expenseRepository.findExpensesByDate(date);
        return expenses.stream().map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ExpenseDTO> findExpensesBetween(LocalDate startDate, LocalDate endDate, Sort sort){
        List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate, sort);
        return expenses.stream().map(expenseMapper :: toDTO).toList();
    }

    public BigDecimal calculateTotalBetween(LocalDate startDate, LocalDate endDate) {
        List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);
        return expenses.stream().map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ExpenseDTO> findByExpenseCategory(ExpenseCategory expenseCategory){
        List<Expense> expenses = expenseRepository.findByExpenseCategory(expenseCategory);
        return expenses.stream().map(expenseMapper :: toDTO).toList();
    }

    public void deleteExpenseById(Long id){
        expenseRepository.deleteExpenseById(id);
    }


}
