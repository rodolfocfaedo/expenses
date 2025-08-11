package com.rodolfocf.expenses_api.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfocf.expenses_api.infrastructure.enums.ExpenseTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_type", nullable = false)
    private ExpenseTypeEnum expenseTypeEnum;

    @Column(name = "description")
    private String description;

    @Column(name = "register_date", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registerDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
