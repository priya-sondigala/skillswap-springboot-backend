package com.example.SkillSwap.controller;

import com.example.SkillSwap.entity.Transaction;
import com.example.SkillSwap.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

     @Autowired
     private WalletService walletService;


     @PostMapping("/add")
     public String addBalance(@RequestParam String learnerId, @RequestParam int amount){
         walletService.addBalance(learnerId, amount);
         return "Balance added successfully";
     }

     @PostMapping("/withdraw")
    public String withdraw(@RequestParam String instructorId,
                           @RequestParam int amount) {

         walletService.withdraw(instructorId, amount);
         return "Withdrawal successful";
     }
}
