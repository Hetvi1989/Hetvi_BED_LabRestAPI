package com.lab6.bed.restApi.security.services;

import java.util.List;

import com.lab6.bed.restApi.security.model.Student;

public interface StudentServices {

	public List<Student> findAll();

	public Student findById(int id);

	public Student save(Student student);

	public void deleteById(int id);

}
