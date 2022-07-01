package com.training.HealthcareApp.Controller;

import com.training.HealthcareApp.DTO.*;
import com.training.HealthcareApp.Misc.UnableToProcessException;
import com.training.HealthcareApp.Misc.ValidityChecks;
import com.training.HealthcareApp.Repositories.doctorRepo;
import com.training.HealthcareApp.Service.UserService;
import com.training.HealthcareApp.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@Slf4j
public class DoctorController {

    private final UserService userService;


    @PostMapping("/list")
    public ResponseEntity<?> getUserDetails(@RequestBody FetchDetails fetchDetails) {
        String id = fetchDetails.getUserId();
        Doctor userDetails = (Doctor) userService.getUser(id, fetchDetails.getEmail());
        if (userDetails != null) {
            return new ResponseEntity<>(ValidityChecks.generateUserData(userDetails), HttpStatus.OK);
        }
        throw new UnableToProcessException("Doctor detail not found");
    }

    @PatchMapping("/patient/DnP")
    public ResponseEntity<?> addIlness(@RequestBody PatientDoctorMapping bookDr)
    {
        PatientDoctorMapping dto = userService.addPrescription(bookDr);
        log.info("saved details");
        if (dto != null) {
            AcknowledgementMessage messageDTO=new AcknowledgementMessage(dto.getId(), "Response Sent successfully",
                    "Diagnosis and Prescription sent");
            return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
        } else {
            throw new UnableToProcessException("Request is not created");
        }

    }


}
