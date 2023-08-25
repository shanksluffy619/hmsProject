package com.OmhniHealthrestapi.service.serviceImpl;

import com.OmhniHealthrestapi.entity.Doctor;
import com.OmhniHealthrestapi.payload.DoctorDTO;
import com.OmhniHealthrestapi.repository.DoctorRepository;
import com.OmhniHealthrestapi.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = findDoctorById(id);
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        Doctor createdDoctor = doctorRepository.save(doctor);
        return modelMapper.map(createdDoctor, DoctorDTO.class);
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor existingDoctor = findDoctorById(id);

        modelMapper.map(doctorDTO, existingDoctor);

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return modelMapper.map(updatedDoctor, DoctorDTO.class);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = findDoctorById(id);
        doctorRepository.delete(doctor);
    }

    private Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + id));
    }
}
