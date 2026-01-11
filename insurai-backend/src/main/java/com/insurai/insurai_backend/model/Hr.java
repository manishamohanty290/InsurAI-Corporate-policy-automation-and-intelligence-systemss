package com.insurai.insurai_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hrs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Full Name

    @Column(nullable = false, unique = true)
    private String email; // Corporate Email

    @Column(nullable = false)
    private String phoneNumber; // Contact Number

    @Column(nullable = false, unique = true)
    private String hrId; // Unique HR ID

    @Column(nullable = false)
    private String password; // Encrypted Password
}
