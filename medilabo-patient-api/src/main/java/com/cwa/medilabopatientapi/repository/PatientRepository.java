package com.cwa.medilabopatientapi.repository;

import com.cwa.medilabopatientapi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
