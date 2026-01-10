package com.example.SkillSwap.controller;

import com.example.SkillSwap.entity.Transaction;
import com.example.SkillSwap.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable String userId){
        return transactionRepository.findAll()
                .stream()
                .filter(t -> t.getFromUser().equals(userId) || t.getToUser().equals(userId)).toList();}
}

