package com.OmhniHealthrestapi.payload;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentDTO {
    private Long id;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private LocalDateTime appointmentDateTime;

    // ... getters, setters, constructors, etc.
}
