package com.training.HealthcareApp.Service;

import com.training.HealthcareApp.DTO.Doctor;
import com.training.HealthcareApp.DTO.Patient;
import com.training.HealthcareApp.DTO.PatientDoctorMapping;

import java.util.List;

public interface UserService {

    Patient savePatient(Patient user);
    Doctor saveDoctor(Doctor user);
    Object getUser(String id, String email);

    Object getDoctor(String Speciality);

    PatientDoctorMapping bookDoctor(PatientDoctorMapping bookObject);
    PatientDoctorMapping addIllness(PatientDoctorMapping bookObject);
    PatientDoctorMapping addPrescription(PatientDoctorMapping bookObject);

    PatientDoctorMapping requestDetails(String requestId);


}
