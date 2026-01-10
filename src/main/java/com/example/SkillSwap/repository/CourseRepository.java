package com.example.SkillSwap.repository;

import com.example.SkillSwap.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
