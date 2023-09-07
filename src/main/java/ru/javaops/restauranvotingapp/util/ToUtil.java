package ru.javaops.restauranvotingapp.util;

import lombok.experimental.UtilityClass;
import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.to.DishTo;
import ru.javaops.restauranvotingapp.to.RestaurantTo;

@UtilityClass
public class ToUtil {

    public static Restaurant createFromRestaurantTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getAddress(), restaurantTo.getName());
    }

    public static Restaurant updateFromRestaurantTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        restaurant.setAddress(restaurantTo.getAddress());
        return restaurant;
    }

    public static Dish updateFromDishTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        dish.setMenuDate(dishTo.getMenuDate());
        return dish;
    }
}
