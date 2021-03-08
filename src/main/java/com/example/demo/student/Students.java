package com.example.demo.student;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "students")

public class Students {
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "student")
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Students(List<Student> students) {
		super();
		this.students = students;
	}
	
	
	

}
