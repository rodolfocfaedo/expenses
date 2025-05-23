package com.rodolfocf.expenses.infrastructure.repository;

import com.rodolfocf.expenses.infrastructure.entities.Expense;
import com.rodolfocf.expenses.infrastructure.enums.ExpenseCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {
    
    @Transactional
    void deleteExpenseById(Long id);

    List<Expense> findExpensesByDate(LocalDate localDate);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate, Sort sort);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Expense> findByExpenseCategory(ExpenseCategory expenseCategory);

}
