package com.training.HealthcareApp.Repositories;

import com.training.HealthcareApp.DTO.PatientDoctorMapping;
import org.springframework.data.repository.CrudRepository;

public interface mappingRepo extends CrudRepository<PatientDoctorMapping,String> {

}
