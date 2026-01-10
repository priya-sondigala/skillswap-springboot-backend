package com.example.SkillSwap.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enrollments")
@Data
public class Enrollment {

    @Id
    private String id;

    private String learnerId;
    private String courseId;

    private String learningStatus; //ENROLLED, IN_PROGRESS, COMPLETED
}
