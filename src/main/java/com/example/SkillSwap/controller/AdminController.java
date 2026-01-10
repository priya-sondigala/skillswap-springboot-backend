package com.example.SkillSwap.controller;

import com.example.SkillSwap.entity.Course;
import com.example.SkillSwap.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private CourseRepository courseRepository;

    @PutMapping("/approve/{courseId}")
    public String approveCourse(@PathVariable String courseId){

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

     course.setStatus("APPROVED");
     courseRepository.save(course);

     return "Course approved by admin";

    }


}

