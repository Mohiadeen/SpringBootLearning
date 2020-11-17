package com.hotel.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hotel.dao.HotelRepository;
import com.hotel.model.Hotel;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
public class HotelController {

	@Autowired
	private HotelRepository repository;

	@GetMapping(path = "/listHotels")
	public Iterable<Hotel> getHotels() {
		return repository.findAll();
	}

	@GetMapping("/hotels/{name}")
	public ResponseEntity<RepresentationModel<?>> gethotelOriginal(@PathVariable String name) {

		Optional<Hotel> hotel = repository.findById(name);
		if (hotel == null)
			throw new NullPointerException();

		RepresentationModel<?> model = RepresentationModel.of(hotel);
		model.add(linkTo(methodOn(this.getClass()).getAllHotels(0, 10, new String[] { "name" })).withRel("hotels"));
		return ResponseEntity.ok(model);
	}

	// Pagination and Sorting example
	@GetMapping(path = "/hotels")
	public ResponseEntity<RepresentationModel<?>> getAllHotels(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "name") String[] sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Hotel> pagedResult = repository.findAll(paging);

		RepresentationModel<?> model = RepresentationModel.of(pagedResult);
		model.add(linkTo(methodOn(this.getClass()).getAllHotels(0, 10, new String[] { "name" })).withRel("hotels"));
		return ResponseEntity.ok(model);
	}

	@PostMapping(path = "/hotels")
	public ResponseEntity<?> saveHotels(@RequestBody @Valid Hotel hotel) {
		System.out.println(hotel.getName());
		hotel = repository.save(hotel);
		if (hotel == null) {
			throw new NullPointerException();
		}
		RepresentationModel<?> model = RepresentationModel.of(hotel);
		model.add(linkTo(methodOn(this.getClass()).getAllHotels(0, 10, new String[] { "name" })).withRel("hotels"));
		return ResponseEntity.ok(model);
	}

	@DeleteMapping(path = "/hotels")
	public ResponseEntity<Link> deleteHotels(@RequestBody @Valid String name) {
		System.out.println("Name ------------- " + name);
		repository.deleteById(name);

		return ResponseEntity
				.ok(linkTo(methodOn(this.getClass()).getAllHotels(0, 10, new String[] { "name" })).withRel("hotels"));
	}

	//Not working 
	@GetMapping("/hotel/{name}")
	public ResponseEntity<MappingJacksonValue> gethotel(@PathVariable String name) {
		Optional<Hotel> hotel = repository.findById(name);
		if (hotel == null)
			throw new NullPointerException();

		// invoking static method filterOutAllExcept()
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
		// creating filter using FilterProvider class

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		// constructor of MappingJacksonValue class that has bean
		MappingJacksonValue mapping = new MappingJacksonValue(hotel);
		// configuringfilters
		mapping.setFilters(filters);

		return ResponseEntity.ok(mapping);
	}
	
	
	@Hidden
	@GetMapping("/hotelFilter/{name}")
	public MappingJacksonValue gethotelF(@PathVariable String name) {
		Optional<Hotel> hotel = repository.findById(name);
		if (hotel == null)
			throw new NullPointerException();

		Set<String> hash_Set = new HashSet<String>();

		hash_Set.add("name");
		hash_Set.add("rating");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(hash_Set);
		// creating filter using FilterProvider class
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		// constructor of MappingJacksonValue class that has bean as constructor argument
		MappingJacksonValue mapping = new MappingJacksonValue(hotel);
		// configuring filters
		mapping.setFilters(filters);

		return mapping;
	}


}
