package ru.javaops.restauranvotingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.repository.DishRepository;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class MenuService {

    private RestaurantRepository repository;

    private DishRepository dishRepository;

    @Transactional
    public Dish save(int restaurantId, Dish dish) {
        dish.setRestaurant(repository.getExisted(restaurantId));
        return dishRepository.save(dish);
    }
}