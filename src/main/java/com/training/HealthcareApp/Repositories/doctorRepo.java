package com.training.HealthcareApp.Repositories;

import com.training.HealthcareApp.DTO.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface doctorRepo extends JpaRepository<Doctor, String> {

    Optional<Doctor> findByIdAndEmail(String id, String email);


    Optional<Doctor> findBySpeciality(String string);
}
