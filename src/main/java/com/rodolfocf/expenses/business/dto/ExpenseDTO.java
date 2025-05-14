package com.rodolfocf.expenses.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfocf.expenses.infrastructure.enums.ExpenseCategory;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Long id;
    private BigDecimal amount;
    private ExpenseCategory expenseCategory;
    private String description;
}
