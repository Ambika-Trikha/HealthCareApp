package com.training.HealthcareApp;

import com.training.HealthcareApp.DTO.Doctor;
import com.training.HealthcareApp.DTO.Patient;
import com.training.HealthcareApp.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@EntityScan({"com/training/HealthcareApp/DTO"})
public class HealthcareAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareAppApplication.class, args);

	}

	 /**@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.savePatient(new Patient("P001", "test@gmail.com", "string1234", "test", "M",
					LocalDate.of(1996, 6, 6), "9986060550"));
			userService.saveDoctor(new Doctor("D001","testdr@gmail.com","qwerty","Testdr","F",LocalDate.of(1990,10,20),
					"0090009090","MBBS","ENT","10"));
			userService.saveDoctor(new Doctor("D002","testdr2@gmail.com","qwerty","Testdr2","F",LocalDate.of(1980,10,20),
					"0090009090","MBBS","Cardio","20"));
		};
**/

}