package com.OmhniHealthrestapi.service;

import com.OmhniHealthrestapi.payload.PatientDTO;

public interface PatientService {
    PatientDTO getPatientById(Long id);
    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO updatePatient(Long id, PatientDTO patientDTO);   
    void deletePatient(Long id);
}
