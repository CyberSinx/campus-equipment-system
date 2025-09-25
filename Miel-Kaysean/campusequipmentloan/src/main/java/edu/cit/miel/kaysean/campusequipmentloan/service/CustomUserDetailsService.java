package edu.cit.miel.kaysean.campusequipmentloan.service;

import edu.cit.miel.kaysean.campusequipmentloan.model.User;
import edu.cit.miel.kaysean.campusequipmentloan.service.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // already encoded
                // If DB stores "STUDENT" or "ADMIN", Spring will add ROLE_ automatically
                .roles(user.getRole())
                .build();
    }
}
