package com.splitwise.repo;

import com.splitwise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Override
    List<Expense> findAll();

    List<Expense> findAllByOrderByDateDesc();
}