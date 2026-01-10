package com.example.SkillSwap.controller;


import com.example.SkillSwap.entity.Enrollment;
import com.example.SkillSwap.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

     @GetMapping("/learner/{learnerId}")
    public List<Enrollment> getLearnerEnrollments(@PathVariable String learnerId){
         return enrollmentRepository.findAll()
                 .stream()
                 .filter(e -> e.getLearnerId().equals(learnerId)).toList();
     }

}
