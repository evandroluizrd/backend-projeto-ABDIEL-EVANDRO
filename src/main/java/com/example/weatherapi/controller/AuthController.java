package com.example.weatherapi.controller;

import com.example.weatherapi.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String senha = credentials.get("senha");

        if ("admin@email.com".equals(email) && "123456".equals(senha)) {
            String token = JwtUtil.generateToken(email);
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(401).body(Map.of("erro", "Credenciais inválidas"));
    }
}
