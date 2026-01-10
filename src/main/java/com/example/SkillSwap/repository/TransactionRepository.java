package com.example.SkillSwap.repository;

import com.example.SkillSwap.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
