package com.example.demo.student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping(path = "/get_students" , produces = {"application/xml"})
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@GetMapping(path = "/get" )
	public List<Student> getStudent() {
		return studentService.getStudents();
	}
	
	@PostMapping(path = "/create")
	public void registerStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	@DeleteMapping(path = "/delete/{studentId}", produces = MediaType.APPLICATION_XML_VALUE)
	public void deleteStudent(@PathVariable("studentId") Long id) {
		studentService.deleteStudent(id);
		
	}
	
	@PutMapping(path = "/update/{studentId}")
	public void updateStudent(
			@PathVariable("studentId") long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email
			) {
		studentService.updateStudent(id, name, email);
	}
}
