package com.cwa.medilaboback.repository;

import com.cwa.medilaboback.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
