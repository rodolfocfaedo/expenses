package com.rodolfocf.expenses.business.mapper;

import com.rodolfocf.expenses.business.dto.ExpenseDTO;
import com.rodolfocf.expenses.infrastructure.entities.Expense;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface IExpenseMapper {

    Expense toEntity(ExpenseDTO expenseDTO);

    ExpenseDTO toDTO(Expense expense);

}
