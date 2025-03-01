package com.micro.userservice.services;

import com.micro.userservice.entities.Hotel;
import com.micro.userservice.entities.Rating;
import com.micro.userservice.entities.User;
import com.micro.userservice.exception.ResourceNotFoundException;
import com.micro.userservice.exception.UserDoesNotExistsException;
import com.micro.userservice.repositories.UserRepository;
import com.micro.userservice.webclient.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serial;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    HotelService hotelService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return List.of(userRepository.findAll().toArray(new User[0]));
    }

    @Override
    public User getUserById(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id "+userId+" not found on server !!"));

        Rating[] array = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        List<Rating> arrayList = Arrays.stream(array).toList();
        List<Rating> list = arrayList.stream().map(rating -> {
//            ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = response.getBody();

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        logger.info("{}",list);
        user.setRatingList(list);
        return user;
    }

    @Override
    public boolean deleteUserById(String userId) {
        Optional<User> optional = userRepository.findById(userId);

        if(optional.isEmpty()){
            throw new UserDoesNotExistsException("User does not exist....");
        }
        userRepository.deleteById(userId);

        return true;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }
}
