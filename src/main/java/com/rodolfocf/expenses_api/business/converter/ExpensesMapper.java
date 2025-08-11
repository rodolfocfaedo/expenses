package com.rodolfocf.expenses_api.business.converter;

import com.rodolfocf.expenses_api.business.dto.ExpensesRequestDTO;
import com.rodolfocf.expenses_api.business.dto.ExpensesResponseDTO;
import com.rodolfocf.expenses_api.infrastructure.entities.Expenses;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ExpensesMapper {

    Expenses toExpensesEntity(ExpensesRequestDTO expensesRequestDTO);

    ExpensesResponseDTO toExpensesDTO(Expenses expenses);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ExpensesRequestDTO dto, @MappingTarget Expenses entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void patchEntityFromDto(ExpensesRequestDTO dto, @MappingTarget Expenses entity);

}
