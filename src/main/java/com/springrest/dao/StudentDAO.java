package com.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.model.Student;

public interface StudentDAO extends JpaRepository<Student, Long>{
	
}
