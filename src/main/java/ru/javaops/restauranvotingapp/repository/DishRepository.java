package ru.javaops.restauranvotingapp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
}
