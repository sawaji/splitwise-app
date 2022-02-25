package com.splitwise.service.impl;

import com.splitwise.dto.ExpenseDTO;
import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import com.splitwise.repo.ExpenseRepository;
import com.splitwise.service.intefaces.ExpenseService;
import com.splitwise.service.intefaces.UserService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserService userService;



    @Override
    public Optional<List<ExpenseDTO>> findAll() {
        List<Expense> expenses = expenseRepository.findAllByOrderByDateDesc();
        return Optional.ofNullable(expenses.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @Override
    public void addExpense(ExpenseDTO expenseDTO) {
        Expense expense = convertToEntity(expenseDTO);
        if (!userService.userExists(expense.getPayer().getUsername())) return;

        expenseRepository.save(expense);
        userService.addPayment(expense.getPayer().getUsername(), expense.getAmount());
    }

    Converter<Long, Instant> toInstant = new AbstractConverter<>() {
        protected Instant convert(Long source) {
            return Instant.ofEpochMilli(source);
        }
    };
    Converter<Instant, Long> toEpoch = new AbstractConverter<>() {
        protected Long convert(Instant source) {
            return source == null ? null : source.getEpochSecond();
        }
    };

    private ExpenseDTO convertToDTO(Expense expense) {
        modelMapper.addConverter(toEpoch);
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        expenseDTO.setUsername(expense.getPayer().getUsername());
        return expenseDTO;
    }

    private Expense convertToEntity(ExpenseDTO expenseDTO) {
        modelMapper.addConverter(toInstant);
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
//        expense.setDate(Instant.ofEpochSecond(expenseDTO.getDate()));
        expense.setPayer(new User.Builder().withUsername(expenseDTO.getUsername()).build());
        return expense;
    }
}