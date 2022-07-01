package com.training.HealthcareApp.Controller;

import com.training.HealthcareApp.DTO.Doctor;
import com.training.HealthcareApp.DTO.Patient;
import com.training.HealthcareApp.DTO.UserCreationMessage;
import com.training.HealthcareApp.Misc.UnableToProcessException;
import com.training.HealthcareApp.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
@Slf4j
public class RegiController {

    private final UserService userService;

    @PostMapping("/patient")
    public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
        Patient dto = userService.savePatient(patient);
        if (dto != null) {
            UserCreationMessage messageDTO = new UserCreationMessage(dto.getId(), "Patient registered successfully");
            return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
        } else {
            throw new UnableToProcessException("Patient is not created");
        }
    }

    @PostMapping("/doctor")
    public ResponseEntity<?> registerDoctor(@RequestBody Doctor doctor) {
        Doctor dto = userService.saveDoctor(doctor);
        if (dto != null) {
            UserCreationMessage messageDTO = new UserCreationMessage(dto.getId(), "Doctor registered successfully");
            return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
        } else {
            throw new UnableToProcessException("Doctor is not created");
        }
    }
}
