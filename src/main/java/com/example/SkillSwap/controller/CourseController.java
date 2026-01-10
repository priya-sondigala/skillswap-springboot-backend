package com.example.SkillSwap.controller;

import com.example.SkillSwap.UserRole;
import com.example.SkillSwap.entity.Course;
import com.example.SkillSwap.entity.User;
import com.example.SkillSwap.repository.CourseRepository;
import com.example.SkillSwap.repository.UserRepository;
import com.example.SkillSwap.services.EnrollmentService;
import com.example.SkillSwap.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserRepository userRepository;

    // ==========================
    // ADD COURSE (Instructor)
    // ==========================
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        course.setStatus("PENDING");
        return courseRepository.save(course);
    }

    // ==========================
    // VIEW APPROVED COURSES
    // ==========================
    @GetMapping("/approved")
    public List<Course> getApprovedCourses() {
        return courseRepository.findAll()
                .stream()
                .filter(course -> "APPROVED".equals(course.getStatus()))
                .toList();
    }

    // ==========================
    // PURCHASE COURSE (Learner)
    // ==========================
    @PostMapping("/purchase/{courseId}/{learnerId}")
    public String purchaseCourse(@PathVariable String courseId,
                                 @PathVariable String learnerId) {

        // 1️⃣ Fetch learner
        User learner = userRepository.findById(learnerId)
                .orElseThrow(() -> new RuntimeException("Learner not found"));

        // 2️⃣ Role validation
        if (learner.getRole() != UserRole.LEARNER) {
            throw new RuntimeException("Only learners can purchase courses");
        }

        // 3️⃣ Fetch course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 4️⃣ Wallet transfer
        walletService.transferCredits(
                learnerId,
                course.getInstructorId(),
                course.getPrice()
        );

        // 5️⃣ Enroll learner
        enrollmentService.enroll(learnerId, courseId);

        return "Course purchased successfully";
    }

    // ==========================
    // APPROVE COURSE (Admin)
    // ==========================
    @PutMapping("/approve/{courseId}")
    public String approveCourse(@PathVariable String courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setStatus("APPROVED");
        courseRepository.save(course);

        return "Course approved successfully";
    }
}
