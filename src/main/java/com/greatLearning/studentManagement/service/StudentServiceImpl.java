package com.greatLearning.studentManagement.service;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

//Define service implementation class for business logic
@Service
public class StudentServiceImpl implements StudentService {

	//Dependency injection for StudentRepository,use and override JPA methods
	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student findById(int theId) {
		// TODO Auto-generated method stub
		return studentRepository.findById(theId).get();
	}

	@Override
	public void save(Student theStudent) {
		// TODO Auto-generated method stub
		studentRepository.save(theStudent);

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(theId);

	}

}