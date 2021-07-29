package com.apurs.microservices.coursesservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
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
	
	@Override
	public Integer countStudentsByCourseId(Integer courseId) {
		if (courseRepository.getById(courseId) == null)
			return null;

		String sql = "SELECT COUNT(\"studentId\") FROM attendance a JOIN course c ON a.\"courseId\" = c.\"id\" WHERE c.\"id\" = " + courseId;
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}
	
	// Courses where a professor is teaching
	@Override
	public List<CourseDTO> findCoursesByProfessorName(String professorName) {
		String[] splitProfessorName = professorName.split("\\s+");
		List<CourseDTO> courses = new ArrayList<CourseDTO>();
		
		String sql = "SELECT * FROM course c JOIN teaching t ON c.\"id\" = t.\"courseId\" JOIN professor p ON t.\"professorId\" = p.\"id\" WHERE (";
		for (int i = 0; i < splitProfessorName.length; i++) {
			sql += "p.\"firstName\" ILIKE '" + splitProfessorName[i].toString() + "%' OR ";
		}
		sql = sql.substring(0, sql.length() - 4) + ") OR (";
		for (int i = 0; i < splitProfessorName.length; i++) {
			sql += "p.\"lastName\" ILIKE '" + splitProfessorName[i].toString() + "%' OR ";
		}
		sql = sql.substring(0, sql.length() - 4) + ")";
		
		courses = jdbcTemplate.query(sql, (rs, rowNum) -> (
					new CourseDTO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("year")
					)));
		
		return courses;
	}
}
