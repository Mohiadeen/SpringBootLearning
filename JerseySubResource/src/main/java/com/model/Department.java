package com.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
	private Integer id;
	private String name;
	private List<Links> links = new ArrayList<Links>();

	public Department() {

	}

	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	// Created Just for showing demo for client
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

	public void addLink(String rel, String link) {
		Links linksBean = new Links(rel, link);
		
		links.add(linksBean);
	}

}