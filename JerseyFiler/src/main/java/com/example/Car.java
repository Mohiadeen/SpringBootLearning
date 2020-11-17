package com.example;

public class Car {

	private String name;
	public String model;
	public int year;
	public String colour;
	public int cylinder;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getCylinder() {
		return cylinder;
	}

	public void setCylinder(int cylinder) {
		this.cylinder = cylinder;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void start() {
		System.out.println("Start the " + this.name);
		accelerate();
	}
	
	
	

	private void accelerate() {
		System.out.println("accelerate the car " + this.name);
	}

	public void stop() {
		System.out.println("stop the " + this.name);
	}

	public Car(String name, String model, int year, String colour, int cylinder) {
		super();
		this.name = name;
		this.model = model;
		this.year = year;
		this.colour = colour;
		this.cylinder = cylinder;
	}

}
