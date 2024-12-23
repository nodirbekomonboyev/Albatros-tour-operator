package com.nodirverse.albatros.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignUpRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate birthday;
}
