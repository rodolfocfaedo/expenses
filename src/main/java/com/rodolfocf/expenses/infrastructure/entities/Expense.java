package com.rodolfocf.expenses.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfocf.expenses.infrastructure.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expenses")
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "expenseCategory")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    private String description;

    

}
