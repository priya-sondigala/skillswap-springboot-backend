package com.example.SkillSwap.repository;


import com.example.SkillSwap.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
