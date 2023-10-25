package com.uio443.diarybackend.controller;

import com.uio443.diarybackend.model.Login;
import com.uio443.diarybackend.model.User;
import com.uio443.diarybackend.service.LoginService;
import com.uio443.diarybackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/getId")
    public ResponseEntity<Long> getUserById(@RequestBody Login login) {
        Long userId = loginService.getUserId(login);
        if (userId < 0) return new ResponseEntity<>(userId, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody Login login) {
        loginService.addLogin(login);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLoginById(@PathVariable Long id) {
        loginService.deleteLoginByUserId(id);
        return new ResponseEntity<>(String.format("Login information for user with ID: %d has been deleted", id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Integer> updateUser(@RequestBody Login login) {
        int result = loginService.updateLogin(login);
        if (result == 0) return new ResponseEntity<>(0, HttpStatus.OK);
        return new ResponseEntity<>(-2, HttpStatus.UNAUTHORIZED);
    }


}
