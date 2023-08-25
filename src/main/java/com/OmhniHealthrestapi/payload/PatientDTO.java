package com.OmhniHealthrestapi.payload;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

 private LocalDate birthDate;

    // ... getters, setters, constructors, etc.
}
