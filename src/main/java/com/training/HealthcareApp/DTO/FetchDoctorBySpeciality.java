package com.training.HealthcareApp.DTO;

public class FetchDoctorBySpeciality {

    String patientId;
    String desiredSpeciality;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDesiredSpeciality() {
        return desiredSpeciality;
    }

    public void setDesiredSpeciality(String desiredSpeciality) {
        this.desiredSpeciality = desiredSpeciality;
    }
}
