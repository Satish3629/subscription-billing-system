package com.satish.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportsDto {

    private  Long id;
    private Enum reportType;
    private Timestamp generatedAt;

}
