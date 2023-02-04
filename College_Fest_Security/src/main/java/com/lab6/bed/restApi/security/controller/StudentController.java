package com.lab6.bed.restApi.security.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lab6.bed.restApi.security.model.Student;
import com.lab6.bed.restApi.security.services.StudentServices;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentServices studentServices;

	@RequestMapping("/list")
	public String listStudents(Model model) {
		System.out.println(" Inside the list controller :::: ");

		// get Students details from db
		List<Student> students = studentServices.findAll();

		// add to the spring model
		model.addAttribute("students", students);

		return "list-students";
	}

	@RequestMapping("/addStudents")
	public String addStudents(Model model) {

		// create model attribute to bind form data
		Student student = new Student();

		model.addAttribute("student", student);

		return "student-form";
	}

	@RequestMapping("/updateStudent")
	public String updateStudent(@RequestParam("studentId") int id, Model model) {

		// get the Student from the service
		Student student = studentServices.findById(id);

		// set Student as a model attribute to pre-populate the form
		model.addAttribute("student", student);

		// send over to our form
		return "student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		System.out.println(id);
		System.out.println(" ==================================================");
		Student student;
		if (id != 0) {
			student = studentServices.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		} else
			student = new Student(firstName, lastName, course, country);
		// save the Student Record
		studentServices.save(student);

		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {

		// delete the Student Record
		studentServices.deleteById(id);

		// redirect to /student/list
		return "redirect:/students/list";

	}

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
