package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "management/api/student")
public class StudentManagementController {
	
	private final StudentController studentController;

	@Autowired
	public StudentManagementController(StudentController studentController) {
		super();
		this.studentController = studentController;
	}
	
	// hasRole("ROLE_") hasAnyRole("ROLE_") hasAuthority("permission") hasAnyAuthority("permission")	
	
	@PostMapping(value = "new")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		studentController.registerStudent(student);
	}
	@GetMapping(value = "all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public Students getAllStudents() {
		return studentController.getStudents();
	}
	@PreAuthorize("hasAuthority('student:write')")
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable long studentId) {
		studentController.deleteStudent(studentId);
	}
	@PreAuthorize("hasAuthority('student:write')")
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable long studentId,@RequestBody String name,@RequestBody String email) {
		studentController.updateStudent(studentId, name, email);
	}
	
	

}
