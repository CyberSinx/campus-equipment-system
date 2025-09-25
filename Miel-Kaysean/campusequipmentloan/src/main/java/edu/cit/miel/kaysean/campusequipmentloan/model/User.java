package edu.cit.miel.kaysean.campusequipmentloan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users") // avoid conflict with reserved "user" keyword
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // must be encoded (BCrypt)

    @Column(nullable = false)
    private String role = "ROLE_STUDENT"; // default role
}
