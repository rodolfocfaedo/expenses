package com.rodolfocf.expenses_api.infrastructure.repositories;

import com.rodolfocf.expenses_api.infrastructure.entities.Expenses;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    List<Expenses> findByRegisterDateBetween(LocalDate startDate, LocalDate endDate);

    @Transactional
    boolean deleteExpenseById(Long id);
}
