package com.cwa.medilaboback.controller;

import com.cwa.medilaboback.exception.ApiException;
import com.cwa.medilaboback.entity.Patient;
import com.cwa.medilaboback.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        return optionalPatient.map(ResponseEntity::ok).orElseThrow(() -> new ApiException("User not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientRepository.save(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient updatedPatient = optionalPatient.get();
            updatedPatient.setPhoneNumber(patient.getPhoneNumber());
            updatedPatient.setAddress(patient.getAddress());
            patientRepository.save(updatedPatient);
            return ResponseEntity.ok(updatedPatient);
        }
        throw new ApiException("User not found with id: " + id);
    }
}
