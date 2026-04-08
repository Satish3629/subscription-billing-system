package com.satish.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name="reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_type", nullable = false)
    private Enum reportType;

    @Column(name = "generated_at", nullable = false)
    private Timestamp genteratedAt;

}
