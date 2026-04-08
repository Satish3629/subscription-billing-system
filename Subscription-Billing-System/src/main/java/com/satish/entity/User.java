package com.satish.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    public User(Long id, String name, String email, String password, String phone, Long roleId, Boolean isActive, LocalDate createdAt, LocalDate updatedAt) {
        this.name=name;
        this.email=email;
        this.password=password;
        this.phone=phone;
        this.isActive=isActive;
    }

}
