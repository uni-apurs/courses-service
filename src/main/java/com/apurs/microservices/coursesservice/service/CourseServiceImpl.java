package com.apurs.microservices.coursesservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.apurs.microservices.coursesservice.dto.CourseCreateDTO;
import com.apurs.microservices.coursesservice.dto.CourseDTO;
import com.apurs.microservices.coursesservice.dto.CourseUpdateDTO;
import com.apurs.microservices.coursesservice.model.Course;
import com.apurs.microservices.coursesservice.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository courseRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<CourseDTO> findAll() {
		List<Course> courses = courseRepository.findAll();
		List<CourseDTO> dtos = new ArrayList<CourseDTO>();
		
		for(Course course : courses)
			dtos.add(modelMapper.map(course, CourseDTO.class));
		
		return dtos;
	}

	@Override
	public CourseDTO findOne(Integer id) {
		Course course = courseRepository.getById(id);
		return modelMapper.map(course, CourseDTO.class);
	}

	@Override
	public CourseDTO insert(CourseCreateDTO course) {
		Course createCourse = modelMapper.map(course, Course.class);
		createCourse = courseRepository.save(createCourse);
		return modelMapper.map(createCourse, CourseDTO.class);
	}

	@Override
	public CourseDTO update(CourseUpdateDTO course) {
		if(!courseRepository.existsById(course.getId()))
			return null;
		
		Course tempCourse = courseRepository.getById(course.getId());
		Course updatedCourse = modelMapper.map(course, Course.class);
		updatedCourse.setCreatedAt(tempCourse.getCreatedAt());
		updatedCourse = courseRepository.save(updatedCourse);
		return modelMapper.map(updatedCourse, CourseDTO.class);
	}

	@Override
	public boolean delete(Integer id) {
		if(!courseRepository.existsById(id))
			return false;
		courseRepository.deleteById(id);
		return true;
	}
}
