package com.example.controller;

import com.example.DTO.AccountDTO;
import com.example.model.AccountModel;
import com.example.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody AccountModel accountModel) {
        return accountService.signUp(accountModel);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AccountDTO accountDTO) {
        return accountService.signIn(accountDTO);
    }
}
