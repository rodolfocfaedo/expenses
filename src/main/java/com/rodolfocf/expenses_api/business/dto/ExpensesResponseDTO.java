package com.rodolfocf.expenses_api.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfocf.expenses_api.infrastructure.enums.ExpenseTypeEnum;
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
public class ExpensesResponseDTO {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registerDate;
    private String name;
    private BigDecimal amount;
    private ExpenseTypeEnum expenseTypeEnum;
    private String description;
}
