package com.training.HealthcareApp.Repositories;

import com.training.HealthcareApp.DTO.Doctor;
import com.training.HealthcareApp.DTO.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface patientRepo extends CrudRepository<Patient, String> {

    Optional<Patient> findByIdAndEmail(String id, String email);
}
