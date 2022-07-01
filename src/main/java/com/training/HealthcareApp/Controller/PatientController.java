package com.training.HealthcareApp.Controller;

import com.training.HealthcareApp.DTO.*;
import com.training.HealthcareApp.Misc.UnableToProcessException;
import com.training.HealthcareApp.Misc.ValidityChecks;
import com.training.HealthcareApp.Repositories.doctorRepo;
import com.training.HealthcareApp.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
@Slf4j

public class PatientController {

    private final UserService userService;
    doctorRepo doctorRepository;

    @PostMapping("/list")
    public ResponseEntity<?> getUserDetails(@RequestBody FetchDetails fetchDetails) {
        String id = fetchDetails.getUserId();
        Patient userDetails = (Patient)userService.getUser (id, fetchDetails.getEmail());
        if (userDetails != null) {
            return new ResponseEntity<>(ValidityChecks.generateUserData(userDetails), HttpStatus.OK);
        }
        throw new UnableToProcessException("Patient detail not found");
    }

    @PostMapping("/getDoctor")
    public ResponseEntity<?> getDoctorBySpeciality(@RequestBody FetchDoctorBySpeciality fetchDoctor)
    {
        String speciality=fetchDoctor.getDesiredSpeciality();
        Doctor details= (Doctor)userService.getDoctor(speciality);
        return new ResponseEntity<>(ValidityChecks.generateUserData(details),HttpStatus.OK);
    }

    @PostMapping("/getDoctor/select")
    public ResponseEntity<?> selectDoctor(@RequestBody PatientDoctorMapping bookDr)
    {
        PatientDoctorMapping dto = userService.bookDoctor(bookDr);
        log.info("saved");
        if (dto != null) {
            UserCreationMessage messageDTO = new UserCreationMessage(dto.getId(), "selection successfull");
            return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
        } else {
            throw new UnableToProcessException("Request is not created");
        }

    }

    @PatchMapping("/getDoctor/addIllness")
    public ResponseEntity<?> updateIllness(@RequestBody PatientDoctorMapping bookDr)
    {
        PatientDoctorMapping dto = userService.addIllness(bookDr);
        //Doctor dr=doctorRepository.findById(dto.getDoctorId());
        if (dto != null) {
            AcknowledgementMessage messageDTO=new AcknowledgementMessage(dto.getId(), "Email Sent successfully",
                    "Concern has been noted, Doctor will contact you accordingly");
            return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
        } else {
            throw new UnableToProcessException("Not updated");
        }

    }

    @PostMapping("/getPrescription")
    public ResponseEntity<?> getPrescription(@RequestBody String requestID) {
        PatientDoctorMapping allDetails= userService.requestDetails(requestID);
        if (allDetails != null) {
            return new ResponseEntity<>(ValidityChecks.generatePrescription(allDetails), HttpStatus.OK);
        }
        throw new UnableToProcessException("Prescription not found");
    }

}
