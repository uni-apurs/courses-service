package com.apurs.microservices.coursesservice.service;

import java.util.List;

import com.apurs.microservices.coursesservice.dto.CourseCreateDTO;
import com.apurs.microservices.coursesservice.dto.CourseDTO;
import com.apurs.microservices.coursesservice.dto.CourseUpdateDTO;

public interface CourseService {
	public abstract List<CourseDTO> findAll();
	public abstract CourseDTO findOne(Integer id);
	public abstract CourseDTO insert(CourseCreateDTO course);
	public abstract CourseDTO update(CourseUpdateDTO course);
	public abstract boolean delete(Integer id);
	
	public abstract Integer countStudentsByCourseId(Integer courseId);
	public abstract List<CourseDTO> findCoursesByProfessorName(String professorName);
}
