package edu.cit.miel.kaysean.campusequipmentloan.controller;

import edu.cit.miel.kaysean.campusequipmentloan.model.User;
import edu.cit.miel.kaysean.campusequipmentloan.service.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Register new user
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return "❌ Username already taken!";
        }

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // enforce role
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("STUDENT");
        } else if (!user.getRole().equalsIgnoreCase("ADMIN")) {
            // anything other than ADMIN is forced to STUDENT
            user.setRole("STUDENT");
        } else {
            user.setRole("ADMIN");
        }

        userRepo.save(user);
        return "✅ User registered successfully: " + user.getUsername() + " (" + user.getRole() + ")";
    }

    // Check logged-in user
    @GetMapping("/me")
    public User me(
            @org.springframework.security.core.annotation.AuthenticationPrincipal
            org.springframework.security.core.userdetails.User principal
    ) {
        return userRepo.findByUsername(principal.getUsername())
                .map(user -> {
                    user.setPassword(null); // hide password from response
                    return user;
                })
                .orElse(null);
    }
}
