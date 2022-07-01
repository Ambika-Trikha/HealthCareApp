package com.training.HealthcareApp.Misc;

import com.training.HealthcareApp.DTO.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidityChecks {

    public static boolean ValidPatient(Patient patient) {
        log.info("checking validity", patient.getName());
        if (isStringNotNull(patient.getName(),2))
        {
            if (isStringNotNull(patient.getPassword(),6))
            {
                if (isStringNotNull(patient.getPhoneNumber()) && isStringNotNull(patient.getEmail()) && patient.getDateOfBirth() != null)
                {
                    return true;
                } else
                {
                    throw new UnableToProcessException("Please enter all the required fields to register");
                }
            } else
            {
                throw new UnableToProcessException("Password is either empty or doesn't match the minimum characters of 6");
            }
        }
        throw new UnableToProcessException("Username is either empty or doesn't match the minimum characters of 3");
    }

    public static boolean ValidDoctor(Doctor doctor) {
        if (isStringNotNull(doctor.getName(), 2)) {
            if (isStringNotNull(doctor.getPassword(), 6)) {
                if (isStringNotNull(doctor.getPhoneNumber())
                        && isStringNotNull(doctor.getQualification()) && doctor.getExperience() != null
                        && isStringNotNull(doctor.getSpeciality()) && isStringNotNull(doctor.getEmail()) && doctor.getDateOfBirth() != null) {
                    return true;
                } else {
                    throw new UnableToProcessException("Please enter all the required fields to register");
                }
            } else {
                throw new UnableToProcessException("Password is either empty or doesn't match the minimum characters of 6");
            }
        }
        throw new UnableToProcessException("Username is either empty or doesn't match the minimum characters of 3");
    }

    private static boolean isStringNotNull(String value) {
        return isStringNotNull(value, 0);
    }

    private static boolean isStringNotNull(String value, int length) {
        if (length == 0) {
            return value != null && !value.trim().isEmpty();
        } else {
            return value != null && !value.trim().isEmpty() && value.trim().length() > length;
        }

    }

    public static User generateUserData(Patient patient) {
        User userDTO = new User();
        userDTO.setRole(patient.getAuthorities().toString().replace("[", "").replace("]", ""));
        userDTO.setDateOfBirth(patient.getDateOfBirth());
        userDTO.setEmail(patient.getEmail());
        userDTO.setId(patient.getId());
        userDTO.setName(patient.getName());
        userDTO.setPhoneNumber(patient.getPhoneNumber());
        userDTO.setGender(patient.getGender());
        return userDTO;
    }

    public static DoctorSpecific generateUserData(Doctor doctor) {
        DoctorSpecific doctorSpecific = new DoctorSpecific();
        doctorSpecific.setRole(doctor.getAuthorities().toString().replace("[", "").replace("]", ""));
        doctorSpecific.setDateOfBirth(doctor.getDateOfBirth());
        doctorSpecific.setEmail(doctor.getEmail());
        doctorSpecific.setId(doctor.getId());
        doctorSpecific.setName(doctor.getName());
        doctorSpecific.setExperience(doctor.getExperience());
        doctorSpecific.setPhoneNumber(doctor.getPhoneNumber());
        doctorSpecific.setGender(doctor.getGender());
        doctorSpecific.setEducation(doctor.getQualification());
        doctorSpecific.setSpeciality(doctor.getSpeciality());
        return doctorSpecific;
    }

    public static PatientDoctorMapping generatePrescription(PatientDoctorMapping record)
    {
        PatientDoctorMapping newPrescription=new PatientDoctorMapping();
        newPrescription.setId(record.getId());
        newPrescription.setPatientId(record.getPatientId());
        newPrescription.setDoctorId(record.getDoctorId());
        newPrescription.setDiagnosis(record.getDiagnosis());
        newPrescription.setPrescription(record.getPrescription());
        return newPrescription;
    }
}
