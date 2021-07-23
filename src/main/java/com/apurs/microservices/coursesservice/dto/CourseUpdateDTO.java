package com.apurs.microservices.coursesservice.dto;

import java.time.ZonedDateTime;

public class CourseUpdateDTO {
	private int id;
	private String name;
	private String year;
	private ZonedDateTime updatedAt;
	
	public CourseUpdateDTO(int id, String name, String year) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.setUpdatedAt(ZonedDateTime.now());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
