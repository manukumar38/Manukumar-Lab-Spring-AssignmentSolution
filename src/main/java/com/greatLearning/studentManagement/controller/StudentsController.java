package com.greatLearning.studentManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;

//controller class for processing incoming REST API requests
@Controller
@RequestMapping("/students")
public class StudentsController {

	//Enables automatic dependency injection for studentservice class
	@Autowired
	private StudentService studentService;

	//mapping to find the list of students under /students/list
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> theStudents = studentService.findAll();
		theModel.addAttribute("Students", theStudents);
		return "list-Students";
	}

	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		// create model attribute to bind form data
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		// get the Student from the service
		Student theStudent = studentService.findById(theId);
		// set Student as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);
		// send over to our form
		return "Student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		} else
			theStudent = new Student(name, department, country);
		// save the Student
		studentService.save(theStudent);
		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		// delete the Student
		studentService.deleteById(theId);
		// redirect to /Students/list
		return "redirect:/students/list";
	}

	//Display the msg if user is not authorized to create/update/delete/view data
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}
}
