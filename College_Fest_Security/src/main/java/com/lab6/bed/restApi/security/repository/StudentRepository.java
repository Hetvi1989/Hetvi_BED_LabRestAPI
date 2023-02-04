package com.lab6.bed.restApi.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab6.bed.restApi.security.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
