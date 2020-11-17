package com.example;

public class Testing {

	public static void main(String[] args) {

	
		
		
		PremiumCar mazdaPremium = new PremiumCar("MazdaP", "CX-3", 2019, "Red", 6, "GPS", "Lether Seat", "Sunroof");
		mazdaPremium.start();
		mazdaPremium.stop();

		PremiumCar audiPremium = new PremiumCar("Audi", "A-6", 2019, "Black", 6, "Apple Car Play", "Heated Lether Seat",
				"Moonroof");
		audiPremium.start("PowerStart");
		audiPremium.stop();
	}

}
