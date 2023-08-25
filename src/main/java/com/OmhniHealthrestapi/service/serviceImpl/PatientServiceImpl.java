package com.OmhniHealthrestapi.service.serviceImpl;

import com.OmhniHealthrestapi.entity.Patient;
import com.OmhniHealthrestapi.payload.PatientDTO;
import com.OmhniHealthrestapi.repository.PatientRepository;
import com.OmhniHealthrestapi.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityNotFoundException;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = findPatientById(id);
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = MaptoEntity(patientDTO);
        Patient patient1 = patientRepository.save(patient);
        PatientDTO patientDTO1 = mapToDto(patient1);
return patientDTO1;
    }

    private PatientDTO mapToDto(Patient patient1) {
        PatientDTO patientDTO = modelMapper.map(patient1, PatientDTO.class);
        return patientDTO;
    }

    private Patient MaptoEntity(PatientDTO patientDTO) {

        Patient patient = modelMapper.map(patientDTO, Patient.class);
return  patient;
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existingPatient = findPatientById(id);

        modelMapper.map(patientDTO, existingPatient);

        Patient updatedPatient = patientRepository.save(existingPatient);
        return modelMapper.map(updatedPatient, PatientDTO.class);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = findPatientById(id);
        patientRepository.delete(patient);
    }

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
    }
}

