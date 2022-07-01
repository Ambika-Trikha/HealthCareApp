package com.training.HealthcareApp.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Mapping")
public class PatientDoctorMapping {

    @Id
    @GenericGenerator(name = "id", strategy = "com.training.HealthcareApp.Misc.RequestIdGenerator")
    @GeneratedValue(generator = "id")
    String id;

    @NotNull
    String patientId;

    @NotNull
    String doctorId;

    String illness;

    String diagnosis;

    String prescription;

}
