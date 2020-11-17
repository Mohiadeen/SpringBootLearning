package com.hotel.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Hotel;

@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, String> {
}