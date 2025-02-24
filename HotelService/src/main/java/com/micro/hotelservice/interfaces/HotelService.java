package com.micro.hotelservice.interfaces;

import com.micro.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);

}
