package com.example.SkillSwap.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data
public class Transaction {

    @Id
    private String id;

    private String fromUser;
    private String toUser;

    private int amount;
    private LocalDateTime date;
}
