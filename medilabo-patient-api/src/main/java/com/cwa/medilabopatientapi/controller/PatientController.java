package com.cwa.medilabopatientapi.controller;

import com.cwa.medilabopatientapi.exception.ApiException;
import com.cwa.medilabopatientapi.entity.Patient;
import com.cwa.medilabopatientapi.repository.PatientRepository;
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
    private static final String USER_NOT_FOUND_MESSAGE = "User not found with id: %s";

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        return optionalPatient.map(ResponseEntity::ok).orElseThrow(() -> new ApiException(String.format(USER_NOT_FOUND_MESSAGE, id)));
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
        throw new ApiException(String.format(USER_NOT_FOUND_MESSAGE, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            patientRepository.delete(optionalPatient.get());
            return ResponseEntity.ok().build();
        }
        throw new ApiException(String.format(USER_NOT_FOUND_MESSAGE, id));
    }
}
