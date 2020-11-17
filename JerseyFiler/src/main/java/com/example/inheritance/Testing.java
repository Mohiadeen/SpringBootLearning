package com.example.inheritance;

public class Testing {

	public static void main(String[] args) {

		Car hundai = new Car("Elentra", "Hundai", 2019, "Yellow", 4);
		hundai.start();
		hundai.stop();

		CarInterface mazda = new Car("Mazda", "CX-3", 2019, "Black", 6);
		mazda.start();
		mazda.stop();

		PremiumCar mazdaPremium = new PremiumCar("MazdaP", "CX-3", 2019, "Red", 6, "GPS", "Lether Seat", "Sunroof");
		mazdaPremium.start();
		mazdaPremium.stop();

		PremiumCar audiPremium = new PremiumCar("Audi", "A-6", 2019, "Black", 6, "Apple Car Play", "Heated Lether Seat",
				"Moonroof");
		audiPremium.start();
		audiPremium.stop();
	}

}
