package com.splitwise.service.impl;

import com.splitwise.entity.User;
import com.splitwise.repo.UserRepository;
import com.splitwise.service.intefaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addPayment(String username, BigDecimal amount) {
        Optional<List<User>> balance = getBalance();
        log.info(String.format("payer:%s - %.2f between %d",username, amount, balance.map(List::size).orElse(0)));
        balance.ifPresent(list -> list.stream()
                .forEach(user -> {
                    log.info(String.format("BEFORE \t%s: %.2f",user.getUsername(), user.getBalance()));
                    BigDecimal needToPay = amount.divide(BigDecimal.valueOf(list.size()), RoundingMode.HALF_UP);
                    if (user.getUsername().equals(username)){
                        needToPay = needToPay.subtract(amount);
                    }
                    user.setBalance(
                            user.getBalance()
                                    .add(needToPay));
                    userRepository.save(user);
                    log.info(String.format("AFTER \t%s: %.2f",user.getUsername(), user.getBalance()));
                }));
    }

    @Override
    public Optional<List<User>> getBalance() {
        return Optional.ofNullable(userRepository.findAll());
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findById(username);
    }
}