package com.OmhniHealthrestapi.service;

import com.OmhniHealthrestapi.payload.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO getAppointmentById(Long id);
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
    List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId);
    List<AppointmentDTO> getAppointmentsByPatientId(Long patientId);

    List<AppointmentDTO> getAllappointment();
}

