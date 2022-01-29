package com.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.dao.StudentDAO;
import com.springrest.model.Student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/student")
public class StudentController {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private StudentDAO studentDAO;

	@GetMapping("{id}")
	@Operation(summary = "Used to get info of Student with given id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Fetched Student with given id",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Student not found",
					content = @Content)
	})
	public Student getStudent(@PathVariable Long id) {
		return studentDAO.findById(id).get();
	}

	@PostMapping
	public Student saveStudent(@RequestBody Student student) throws JsonProcessingException {
		String studentStr = objectMapper.writeValueAsString(student); // Used to convert object to String
		Student std = objectMapper.readValue(studentStr, Student.class); // Used to convert String to object
		System.out.println("Value of Student in json: " + studentStr);
		System.out.println("Value of Student bean in object form: " + std);
		Student savedStudent = studentDAO.save(student);
		Link selfRelLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getStudent(savedStudent.getStudentId()))
				.withSelfRel();
		savedStudent.add(selfRelLink);
		return savedStudent;
	}
}
