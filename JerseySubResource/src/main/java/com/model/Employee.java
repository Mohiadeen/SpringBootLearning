package com.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private Integer id;
	private String name;
	private List<Department> departments = new ArrayList<Department>();

	public Employee() {

	}

	public Employee(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Employee(Integer id, String name, List<Department> departments) {
		super();
		this.id = id;
		this.name = name;
		this.departments = departments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	// Created Just for showing demo for client
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}