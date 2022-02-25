package com.splitwise.service.intefaces;

import com.splitwise.dto.ExpenseDTO;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    void addExpense(ExpenseDTO expenseDTO);
    Optional<List<ExpenseDTO>> findAll();
}