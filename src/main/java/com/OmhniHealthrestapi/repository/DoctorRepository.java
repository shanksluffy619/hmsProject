package com.OmhniHealthrestapi.repository;
import com.OmhniHealthrestapi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // You can add custom query methods here if needed
}
