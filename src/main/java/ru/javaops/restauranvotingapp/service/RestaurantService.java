package ru.javaops.restauranvotingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.to.RestaurantTo;
import ru.javaops.restauranvotingapp.util.ToUtil;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository repository;

    @Transactional
    //@CachePut(value = "restaurants", key = "#restaurantId")
    public Restaurant update(int restaurantId, RestaurantTo restaurantTo) {
        Restaurant restaurant = ToUtil.updateFromRestaurantTo(repository.getExisted(restaurantId), restaurantTo);
        return repository.save(restaurant);
    }
}
