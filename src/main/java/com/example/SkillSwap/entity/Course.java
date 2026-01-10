package com.example.SkillSwap.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
@Data
public class Course {

    @Id
    private String id;

    private String instructorId;
    private String skillName;
    private String description;
    private int price;

    private String status; //PENDING, APPROVED
}
