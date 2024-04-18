package com.example.demo.controller;


import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.ResponseRequest;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAdminController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseRequest> signUp(@RequestBody AuthRequest ar){
        return ResponseEntity.ok(authService.signup(ar));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseRequest> signIn(@RequestBody AuthRequest ar){
        return ResponseEntity.ok(authService.signIn(ar));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseRequest> refreshToken(@RequestBody AuthRequest ar){
        return ResponseEntity.ok(authService.refreshToken(ar));
    }
}
