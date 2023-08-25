package com.OmhniHealthrestapi.service.serviceImpl;

import com.OmhniHealthrestapi.entity.Appointment;
import com.OmhniHealthrestapi.entity.Doctor;
import com.OmhniHealthrestapi.entity.Patient;
import com.OmhniHealthrestapi.exception.ResourceNotFound;
import com.OmhniHealthrestapi.payload.AppointmentDTO;
import com.OmhniHealthrestapi.payload.DoctorDTO;
import com.OmhniHealthrestapi.payload.PatientDTO;
import com.OmhniHealthrestapi.repository.AppointmentRepository;
import com.OmhniHealthrestapi.repository.DoctorRepository;
import com.OmhniHealthrestapi.repository.PatientRepository;
import com.OmhniHealthrestapi.service.AppointmentService;
import com.OmhniHealthrestapi.service.DoctorService;
import com.OmhniHealthrestapi.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment;
        appointment = findAppointmentById(id);
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = maptoEntity(appointmentDTO);

        // Save the appointment entity
        Appointment savedAppointment = appointmentRepository.save(appointment);
        AppointmentDTO savedAppointmentDTO = MaptoDto(savedAppointment);
        Optional<Doctor> byId = doctorRepository.findById(savedAppointmentDTO.getDoctor().getId());
        Doctor doctor = byId.get();
        savedAppointmentDTO.setDoctor(mapTODoctorDto(doctor));

        Optional<Patient> byId1 = patientRepository.findById(savedAppointmentDTO.getPatient().getId());
        Patient patient = byId1.get();
        savedAppointmentDTO.setPatient(maptoPatientDto(patient));
        return savedAppointmentDTO;
    }

    private AppointmentDTO MaptoDto(Appointment appointment1) {

        AppointmentDTO map = modelMapper.map(appointment1, AppointmentDTO.class);
        return map;
    }

    private Appointment maptoEntity(AppointmentDTO appointmentDTO) {
        Appointment map = modelMapper.map(appointmentDTO, Appointment.class);
return map;
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("appointment", "id", id)
        );
        DoctorDTO doctordto = appointmentDTO.getDoctor();
        Doctor doctor1 = maptoDoctorEntity(doctordto);
        appointment.setDoctor(doctor1);

        PatientDTO patientdto = appointmentDTO.getPatient();
        Patient patient = maptoPatientEntity(patientdto);
        appointment.setPatient(patient);

        appointment.setId(appointmentDTO.getId());
        appointment.setAppointmentDateTime(appointmentDTO.getAppointmentDateTime());

        Appointment save = appointmentRepository.save(appointment);
        AppointmentDTO appointmentDTO1 = MaptoDto(save);

        Optional<Patient> byId = patientRepository.findById(appointmentDTO1.getPatient().getId());
        Patient patient1 = byId.get();
        appointmentDTO1.setPatient(maptoPatientDto(patient1));
        Optional<Doctor> byId1 = doctorRepository.findById(appointmentDTO1.getDoctor().getId());
        Doctor doctor = byId1.get();
        appointmentDTO1.setDoctor(mapTODoctorDto(doctor));
        appointmentDTO1.setAppointmentDateTime(save.getAppointmentDateTime());


        return appointmentDTO1;


    }

    private Patient maptoPatientEntity(PatientDTO patientdto) {
        Patient map = modelMapper.map(patientdto, Patient.class);
        return map;

    }

    private Doctor maptoDoctorEntity(DoctorDTO doctor) {
        Doctor map = modelMapper.map(doctor, Doctor.class);
        return map;
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = findAppointmentById(id);
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> byDoctorId = appointmentRepository.findByDoctorId(doctorId);
        if (byDoctorId==null){
            throw new ResourceNotFound("doctor","id",doctorId);
        }
        List<AppointmentDTO> collect = byDoctorId.stream().map(appointments -> MaptoDto(appointments)).collect(Collectors.toList());

        return collect;



    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllappointment() {

        List<Appointment> all = appointmentRepository.findAll();

        List<AppointmentDTO> dtos = new ArrayList<>();

        for ( Appointment    appointment        :    all  ){
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setAppointmentDateTime(appointment.getAppointmentDateTime());
            appointmentDTO.setDoctor(mapTODoctorDto(appointment.getDoctor()));
            appointmentDTO.setPatient(maptoPatientDto(appointment.getPatient()));
            dtos.add(appointmentDTO);

        }



return dtos;



    }

    private PatientDTO maptoPatientDto(Patient patient) {

        PatientDTO map = modelMapper.map(patient, PatientDTO.class);
        return  map;
    }

    private DoctorDTO mapTODoctorDto(Doctor doctor) {

        DoctorDTO map = modelMapper.map(doctor, DoctorDTO.class);
        return  map;

    }

    private Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + id));
    }
}
