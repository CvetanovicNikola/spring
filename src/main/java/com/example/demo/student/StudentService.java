package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class StudentService {
	
	private final StudnetRepository studentRepository;
	
	
	
	public StudentService(StudnetRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> newStudent = studentRepository.findStudentByEmail(student.getEmail());
		if(newStudent.isPresent()) throw new IllegalStateException("Email taken!");
		studentRepository.save(student);
		
	}


	public void deleteStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException(
					String.format("Student with id %s doesnt exist", id));
		}
		studentRepository.deleteById(id);
		
	}

	@Transactional
	public void updateStudent(long id, String name, String email) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException(String.format("Student with id %s doesnt exist", id)));
		
	if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) student.setName(name);;
	
	if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
		Optional<Student> existingEmail = studentRepository.findStudentByEmail(email);
		if(existingEmail.isPresent()) throw new IllegalStateException(String.format("Student with email %s exist", email));
	};
	student.setEmail(email);
	}
}
