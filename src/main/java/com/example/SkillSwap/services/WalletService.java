package com.example.SkillSwap.services;


import com.example.SkillSwap.UserRole;
import com.example.SkillSwap.entity.Transaction;
import com.example.SkillSwap.entity.User;
import com.example.SkillSwap.repository.TransactionRepository;
import com.example.SkillSwap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    TransactionRepository transactionRepository;

    public void transferCredits(String learnerId, String instructorId, int amount){

        User learner = userRepository.findById(learnerId).orElseThrow();
        User instructor = userRepository.findById(instructorId).orElseThrow();

        if (learner.getRole() != UserRole.LEARNER) {
            throw new RuntimeException("Only learners can purchase courses");
        }
        if(instructor.getRole() != UserRole.INSTRUCTOR){
            throw new RuntimeException("Course owner must be an instructor");
        }
        if (amount <=0){
            throw new RuntimeException("Amount must be greater than zero");
        }
        if (learner.getWalletBalance()<amount){
            throw new RuntimeException("Insufficient credits");
        }

        learner.setWalletBalance(learner.getWalletBalance() - amount);
        instructor.setWalletBalance(instructor.getWalletBalance() + amount);

        userRepository.save(learner);
        userRepository.save(instructor);

        Transaction txn = new Transaction();
        txn.setFromUser(learnerId);
        txn.setToUser(instructorId);
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());

        transactionRepository.save(txn);
    }

    public void addBalance(String learnerId, int amount){

        User learner = userRepository.findById(learnerId).orElseThrow(() -> new RuntimeException("User not found"));

        if(learner.getRole() != UserRole.LEARNER){
            throw new RuntimeException("Only learner can add balance");
        }
        if (amount <= 0){
            throw new RuntimeException("Amount must be greater than zero");
        }

        learner.setWalletBalance(learner.getWalletBalance() + amount);
        userRepository.save(learner);

        Transaction txn = new Transaction();
        txn.setFromUser("SYSTEM");
        txn.setToUser(learnerId);
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());

        transactionRepository.save(txn);
    }
    public void withdraw(String instructorId, int amount) {

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (instructor.getRole() != UserRole.INSTRUCTOR) {
            throw new RuntimeException("Only instructors can withdraw");
        }

        if (amount <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        if (instructor.getWalletBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        instructor.setWalletBalance(instructor.getWalletBalance() - amount);
        userRepository.save(instructor);

        Transaction txn = new Transaction();
        txn.setFromUser(instructorId);
        txn.setToUser("BANK");
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());

        transactionRepository.save(txn);
    }
}



