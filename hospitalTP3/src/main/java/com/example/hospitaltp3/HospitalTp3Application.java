package com.example.hospitaltp3;

import com.example.hospitaltp3.enteties.Patient;
import com.example.hospitaltp3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HospitalTp3Application implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(HospitalTp3Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		patientRepository.save(new Patient(null,"hanae",new Date(),false,34));
		patientRepository.save(new Patient(null,"ahmed",new Date(),false,366));
		patientRepository.save(new Patient(null,"lara",new Date(),true,984));
		patientRepository.save(new Patient(null,"hanae",new Date(),false,34));
		patientRepository.save(new Patient(null,"ahmed",new Date(),false,366));
		patientRepository.save(new Patient(null,"lara",new Date(),true,984));

	}
}
