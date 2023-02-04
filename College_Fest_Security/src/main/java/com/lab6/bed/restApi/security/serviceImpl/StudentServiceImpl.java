package com.lab6.bed.restApi.security.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab6.bed.restApi.security.model.Student;
import com.lab6.bed.restApi.security.repository.StudentRepository;
import com.lab6.bed.restApi.security.services.StudentServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentServices {

	private final StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int id) {
		return this.studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("invalid student id"));
	}

	@Override
	public Student save(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public void deleteById(int id) {
		this.studentRepository.deleteById(id);
	}

}
