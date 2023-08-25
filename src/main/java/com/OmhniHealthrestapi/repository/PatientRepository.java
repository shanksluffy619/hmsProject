package com.OmhniHealthrestapi.repository;

import com.OmhniHealthrestapi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // You can add custom query methods here if needed
}
