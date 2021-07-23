package com.apurs.microservices.coursesservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apurs.microservices.coursesservice.dto.CourseCreateDTO;
import com.apurs.microservices.coursesservice.dto.CourseDTO;
import com.apurs.microservices.coursesservice.dto.CourseUpdateDTO;
import com.apurs.microservices.coursesservice.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseRestController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("")
	public List<CourseDTO> getCourse(){
		return courseService.findAll();
	}
	
	@GetMapping("/{id}")
	public CourseDTO getCourse(@PathVariable("id") Integer id) {
		return courseService.findOne(id);
	}
	
	@PostMapping("")
	public ResponseEntity<CourseDTO> insertCourse (@RequestBody CourseCreateDTO course){
		if(courseService.insert(course) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("")
	public ResponseEntity<CourseDTO> updateCourse (@RequestBody CourseUpdateDTO course){
		if(courseService.update(course) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CourseDTO> deleteCourse (@PathVariable("id") Integer id){
		if(courseService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
