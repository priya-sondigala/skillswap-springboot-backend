package com.example.SkillSwap.services;

import com.example.SkillSwap.entity.Enrollment;
import com.example.SkillSwap.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public void enroll(String learnerId, String courseId) {

        Enrollment e = new Enrollment();
        e.setLearnerId(learnerId);
        e.setCourseId(courseId);
        e.setLearningStatus("ENROLLED");

        enrollmentRepository.save(e);
    }
}

