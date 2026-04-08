package com.satish.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "userRoles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_Id", nullable = false)
    private Long userId;

    @Column(name = "role_Id", nullable = false)
    private Long roleId;
}
