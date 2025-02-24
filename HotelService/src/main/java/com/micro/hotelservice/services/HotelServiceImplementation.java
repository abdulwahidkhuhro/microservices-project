package com.micro.hotelservice.services;

import com.micro.hotelservice.entities.Hotel;
import com.micro.hotelservice.exceptions.ResourceNotFoundExcetion;
import com.micro.hotelservice.interfaces.HotelService;
import com.micro.hotelservice.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImplementation implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcetion("Hotel with given id not found!!"));
    }
}
