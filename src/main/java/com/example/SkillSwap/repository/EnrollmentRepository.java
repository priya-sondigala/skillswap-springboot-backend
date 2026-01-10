package com.example.SkillSwap.repository;

import com.example.SkillSwap.entity.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
}
