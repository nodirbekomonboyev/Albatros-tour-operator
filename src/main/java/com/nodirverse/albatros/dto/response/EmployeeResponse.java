package com.nodirverse.albatros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String surname;
    private String imageUrl;
    private String phoneNumber;
    private String position;
}
