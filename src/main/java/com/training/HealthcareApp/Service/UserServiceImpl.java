package com.training.HealthcareApp.Service;

import com.training.HealthcareApp.DTO.Doctor;
import com.training.HealthcareApp.DTO.Patient;
import com.training.HealthcareApp.DTO.PatientDoctorMapping;
import com.training.HealthcareApp.Misc.UnableToProcessException;
import com.training.HealthcareApp.Repositories.doctorRepo;
import com.training.HealthcareApp.Repositories.mappingRepo;
import com.training.HealthcareApp.Repositories.patientRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.training.HealthcareApp.Misc.ValidityChecks.ValidDoctor;
import static com.training.HealthcareApp.Misc.ValidityChecks.ValidPatient;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final patientRepo patientRepository;
    private final doctorRepo doctorRepository;
    private final mappingRepo mappingRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Patient savePatient(Patient patient) {

        ValidPatient(patient);
        if(patient.getId() == null)
        {
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            log.info("Saving new patient {} to the database", patient.getUsername());
            return patientRepository.save(patient);
        }
        else

        throw new UnableToProcessException("Id should not be entered, while registering the patient");
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        ValidDoctor(doctor);
        if(doctor.getId() == null)
        {
            log.info("Saving new doctor {} to the database", doctor.getUsername());
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            return doctorRepository.save(doctor);
        }

        throw new UnableToProcessException("Id should not be entered, while registering the doctor");
    }

    @Override
    public Object getUser(String id, String email) {
        if (id != null && !id.trim().isEmpty() && email != null && !email.trim().isEmpty()) {
            id = id.trim().toUpperCase();
            if (id.startsWith("PAT")) {
                return patientRepository.findByIdAndEmail(id, email).orElseThrow(() ->
                        new UnableToProcessException("Patient not found"));
            } else if (id.startsWith("DOC")) {
                return doctorRepository.findByIdAndEmail(id, email).orElseThrow(() ->
                        new UnableToProcessException("Doctor not found"));
            }
        }
        throw new UnableToProcessException("Please enter valid userId and email");
    }

    @Override
    public Object getDoctor(String Speciality) {
        if(Speciality !=null) {
            return doctorRepository.findBySpeciality(Speciality).orElseThrow(()->
                    new UnableToProcessException("Doctor not found"));
        } 
        throw new UnableToProcessException("Please enter Speciality to process");
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        if (id != null && !id.trim().isEmpty()) {
            id = id.trim().toUpperCase();

            if (id.startsWith("PAT")) {
                Optional<Patient> patientDTO = patientRepository.findById(id);
                return patientDTO.orElseThrow(() -> new UsernameNotFoundException("Patient not found"));
            } else if (id.startsWith("DOC")) {
                Optional<Doctor> doctorDTO = doctorRepository.findById(id);
                return doctorDTO.orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
            }
        }
        throw new UsernameNotFoundException("User Id not found");
    }

    @Override
    public PatientDoctorMapping bookDoctor(PatientDoctorMapping bookUser)
    {
        if(bookUser.getId()==null){
            if(bookUser.getPatientId() !=null && bookUser.getDoctorId() !=null)
                return mappingRepository.save(bookUser);
            else
                throw new UnableToProcessException("Patient ID or Doctor ID is missing");
        }

        else
            throw new UnableToProcessException("Id should not be entered, while making a booking");

    }

    @Override
    public PatientDoctorMapping addIllness(PatientDoctorMapping bookUser)
    {
        if(bookUser.getIllness() !=null){
            if(bookUser.getId() !=null)
            {
                log.info("Added Illness");
                return mappingRepository.save(bookUser);
            }

            else
                throw new UnableToProcessException("Please make booking first");
        }
        else
            throw new UnableToProcessException("Mention Illness");

    }

    @Override
    public PatientDoctorMapping addPrescription(PatientDoctorMapping bookUser)
    {
        if(bookUser.getDiagnosis() !=null && bookUser.getPrescription() !=null){
            log.info("Added prescription");
            return mappingRepository.save(bookUser);
        }

        else
            throw new UnableToProcessException("Either Diagnosis or prescription is missing");

    }

    @Override
    public PatientDoctorMapping requestDetails(String requestId) {
        if(requestId !=null) {
            return mappingRepository.findById(requestId).orElseThrow(()->
                    new UnableToProcessException("Request not found"));
        }
        throw new UnableToProcessException("Please enter Request ID to process");
    }


}
