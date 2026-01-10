package com.example.SkillSwap.entity;


import com.example.SkillSwap.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private UserRole role;

    private int walletBalance;

    public UserRole getRole() {
        return role;
    }

}
