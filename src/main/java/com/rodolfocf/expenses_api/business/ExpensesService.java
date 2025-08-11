package com.rodolfocf.expenses_api.business;

import com.rodolfocf.expenses_api.business.converter.ExpensesMapper;
import com.rodolfocf.expenses_api.business.dto.ExpensesRequestDTO;
import com.rodolfocf.expenses_api.business.dto.ExpensesResponseDTO;
import com.rodolfocf.expenses_api.infrastructure.entities.Expenses;
import com.rodolfocf.expenses_api.infrastructure.exceptions.ResourceNotFoundException;
import com.rodolfocf.expenses_api.infrastructure.repositories.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper expensesMapper;

    public ExpensesResponseDTO registerExpenses(ExpensesRequestDTO expensesRequestDTO) {
        Expenses expensesEntity = expensesMapper.toExpensesEntity(expensesRequestDTO);
        return expensesMapper.toExpensesDTO(expensesRepository.saveAndFlush(expensesEntity));
    }


    public List<ExpensesResponseDTO> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        return expensesRepository.findByRegisterDateBetween(startDate, endDate).stream()
                .map(expensesMapper::toExpensesDTO)
                .collect(Collectors.toList());
    }

    public ExpensesResponseDTO updateExpenseById(Long id, ExpensesRequestDTO expensesRequestDTO) {
        Expenses existing = expensesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));
        expensesMapper.updateEntityFromDto(expensesRequestDTO, existing);
        return expensesMapper.toExpensesDTO(expensesRepository.save(existing));
    }

    public ExpensesResponseDTO patchExpense(Long id, ExpensesRequestDTO expensesRequestDTO) {
        Expenses existing = expensesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));
        expensesMapper.patchEntityFromDto(expensesRequestDTO, existing);
        return expensesMapper.toExpensesDTO(expensesRepository.save(existing));
    }


    public void deleteExpenseById(Long id) {
        if (!expensesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
        expensesRepository.deleteById(id);
    }


}
