package com.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.hotel.dao.HotelRepository;
import com.hotel.model.Hotel;

@SpringBootApplication
@Component
public class HotelApplication implements CommandLineRunner {

	@Autowired
	private HotelRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Executing command line runner");
		repository.save(new Hotel("Travel Safe", "Best place to see winter", "Los Angles", 9));
		repository.save(new Hotel("Covellert Inn", "Best place to see Summer", "New York", 10));
		repository.save(new Hotel("Ouray Inn", "Switzerland of America", "Colorado", 9));
		repository.save(new Hotel("Portland Inn", "Best place to see FALL", "Oregon", 10));
		repository.save(new Hotel("Amsterdam Hostel", "Night life view", "Amsterdam", 10));
	}
}
