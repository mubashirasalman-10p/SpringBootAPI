package com.demo.crudapi.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.crudapi.model.Student;
import com.demo.crudapi.repositry.Repo;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	Repo studentRepo;
	
	@GetMapping("/students")
	  public ResponseEntity<List<Student>> getAllTutorials(@RequestParam(required = false) String title) {
	    try {
	      List<Student> students = new ArrayList<Student>();
	      studentRepo.findAll().forEach(students::add);
	      if (students.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(students, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	  @GetMapping("/students/{id}")
	  public ResponseEntity<Student> getTutorialById(@PathVariable("id") Integer id) {
	    Optional<Student> studentData = studentRepo.findById(id);

	    if (studentData.isPresent()) {
	      return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @PostMapping("/students")
	  public ResponseEntity<Student> createTutorial(@RequestBody Student std) {
	    try {
	      Student newStudent = studentRepo.save(new Student(std.getAge(),std.getName()));
	      return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PutMapping("/studets/{id}")
	  public ResponseEntity<Student> updateTutorial(@PathVariable("id") Integer id, @RequestBody Student std) {
	    Optional<Student> studentData = studentRepo.findById(id);

	    if (studentData.isPresent()) {
	      Student updatedStudent = studentData.get();
	      updatedStudent.setAge(std.getAge());
	      updatedStudent.setName(std.getName());
	      return new ResponseEntity<>(studentRepo.save(updatedStudent), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/students/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Integer id) {
	    try {
	      studentRepo.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping("/students")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    try {
	      studentRepo.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }
}