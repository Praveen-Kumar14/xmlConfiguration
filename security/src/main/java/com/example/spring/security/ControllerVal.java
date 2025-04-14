package com.example.spring.security;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.security.jwt.JwtService;
import com.example.spring.security.user.User;
import com.example.spring.security.user.UserPrincipal;
import com.example.spring.security.user.userRepo;

@RestController
public class ControllerVal {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private userRepo repo;

    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/")
    public String returnVal() {
        return "Welcome back Chief";
    }
    
    @GetMapping("/students")
    public ResponseEntity<?> getStudents() {
        try {
            List<User> students = repo.findAll();
            if (students.isEmpty()) {
                return ResponseEntity
                    .ok()
                    .body(Map.of("message", "No students found in database"));
            }
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(Map.of("error", "Error fetching students: " + e.getMessage()));
        }
    }

    @PostMapping("/students")
    public User addStudent(@RequestBody User student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return repo.save(student);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            if (repo.findByUsername(user.getUsername()) != null) {
                return ResponseEntity
                    .badRequest()
                    .body("Username already exists");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            User savedUser = repo.save(user);
            
            return ResponseEntity
                .ok()
                .body("User registered successfully with username: " + savedUser.getUsername());
        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body("Error during registration: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            User user = repo.findByUsername(loginRequest.getUsername());
            
            if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                UserPrincipal userPrincipal = new UserPrincipal(user);
                String token = jwtService.generateToken(userPrincipal);
                
                return ResponseEntity.ok()
                    .body(Map.of(
                        "status", "success",
                        "message", "Login successful",
                        "username", user.getUsername(),
                        "token", token
                    ));
            }
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("status", "error", "message", "Invalid credentials"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint() {
        return ResponseEntity.ok(Map.of("message", "This is a protected endpoint"));
    }
}
