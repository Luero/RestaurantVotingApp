package ru.javaops.restauranvotingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.repository.DishRepository;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.to.DishTo;
import ru.javaops.restauranvotingapp.util.ToUtil;

@Service
@AllArgsConstructor
public class MenuService {

    private RestaurantRepository restaurantRepository;

    private DishRepository repository;

    @Transactional
    @CacheEvict(value = "menu_for_date", allEntries = true)
    public Dish save(int restaurantId, Dish dish) {
        dish.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return repository.save(dish);
    }

    @Transactional
    public Dish update(int id, DishTo dishTo) {
        Dish dish = ToUtil.updateFromDishTo(repository.getExisted(id), dishTo);
        return repository.save(dish);
    }
}