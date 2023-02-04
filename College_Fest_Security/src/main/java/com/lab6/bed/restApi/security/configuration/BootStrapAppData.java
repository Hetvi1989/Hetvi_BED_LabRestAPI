package com.lab6.bed.restApi.security.configuration;

import javax.transaction.Transactional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lab6.bed.restApi.security.model.Role;
import com.lab6.bed.restApi.security.model.Student;
import com.lab6.bed.restApi.security.model.User;
import com.lab6.bed.restApi.security.repository.StudentRepository;
import com.lab6.bed.restApi.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootStrapAppData {

	private final UserRepository userRepository;

	private final StudentRepository studentRepository;

	private final PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeStudentData(ApplicationReadyEvent readyEvent) {

		Student suresh = new Student("Suresh", "Reddy", "B.Tech", "India");
		Student murali = new Student("Murali", "Mohan", "B.Arch", "Canada");
		Student daniel = new Student("Daniel", "Denson", "B.Tech", "New Zeland");
		Student rupal = new Student("Rupal", "Patel", "Regulatory Affairs", "USA");
		Student tanay = new Student("Tanay", "Gupta", "B.com", "UK");

		studentRepository.save(suresh);
		studentRepository.save(murali);
		studentRepository.save(daniel);
		studentRepository.save(rupal);
		studentRepository.save(tanay);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void initializeUserData(ApplicationReadyEvent readyEvent) {

		User rajesh = new User("Rajesh", passwordEncoder.encode("welcome"));
		User yogesh = new User("Yogesh", passwordEncoder.encode("welcome"));

		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");

		rajesh.addRole(userRole);

		yogesh.addRole(adminRole);
		yogesh.addRole(userRole);

		this.userRepository.save(rajesh);
		this.userRepository.save(yogesh);
	}

}
