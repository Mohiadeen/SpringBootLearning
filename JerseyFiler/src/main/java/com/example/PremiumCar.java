package com.example;

public class PremiumCar extends Car {

	private String navigationSystem;
	public String seatType;
	public String roofType;

	public String getNavigationSystem() {
		return navigationSystem;
	}

	public void setNavigationSystem(String navigationSystem) {
		this.navigationSystem = navigationSystem;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getRoofType() {
		return roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}

	public PremiumCar(String name, String model, int year, String colour, int cylinder, String navigationSystem,
			String seatType, String roofType) {
		super(name, model, year, colour, cylinder);
		this.navigationSystem = navigationSystem;
		this.seatType = seatType;
		this.roofType = roofType;
	}
	
	
	
	public void start(String starttype) {
		System.out.println("Start the Testing " + starttype);

	}
	
	


}
