package ru.javaops.restauranvotingapp.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menu WHERE r.id=?1")
    Optional<Restaurant> getWithDishes(int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menu d WHERE d.menuDate=?1")
    @Cacheable(value = "menu_for_date")
    List<Restaurant> getAllWithMenuByDate(LocalDate date);
}
