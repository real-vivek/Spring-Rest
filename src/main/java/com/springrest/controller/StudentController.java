package com.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.dao.StudentDAO;
import com.springrest.model.Student;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentDAO studentDAO;

	@GetMapping("{id}")
	public Student getStudent(@PathVariable Long id) {
		return studentDAO.findById(id).get();
	}

}
