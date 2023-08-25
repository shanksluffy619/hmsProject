package com.OmhniHealthrestapi.payload;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String specialization;

    // ... getters, setters, constructors, etc.
}
