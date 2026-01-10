package com.example.SkillSwap;


import com.example.SkillSwap.entity.User;
import com.example.SkillSwap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("{userId}/role")
    public String updateRole(@PathVariable String userId,@RequestParam UserRole role){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);
        userRepository.save(user);

        return "Role updated successfully";
    }
}
