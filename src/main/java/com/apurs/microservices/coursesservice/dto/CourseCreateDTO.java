package com.apurs.microservices.coursesservice.dto;

import java.time.ZonedDateTime;

public class CourseCreateDTO {
	private String name;
	private String year;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	
	public CourseCreateDTO(String name, String year) {
		super();
		this.name = name;
		this.year = year;
		this.setCreatedAt(ZonedDateTime.now());
		this.setUpdatedAt(ZonedDateTime.now());
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

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
