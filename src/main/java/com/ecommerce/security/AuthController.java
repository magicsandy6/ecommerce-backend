package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        User user = userService.getUserByEmail(email)
                .orElse(null);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(new HashMap<String, String>() {{
                put("error", "Invalid email or password");
            }}, HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(email);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
