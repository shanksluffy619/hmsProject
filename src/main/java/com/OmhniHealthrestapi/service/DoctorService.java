package com.OmhniHealthrestapi.service;

import com.OmhniHealthrestapi.payload.DoctorDTO;

public interface DoctorService {
    DoctorDTO getDoctorById(Long id);
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);
    void deleteDoctor(Long id);
}
