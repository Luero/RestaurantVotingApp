package ru.javaops.restauranvotingapp.util;

import lombok.experimental.UtilityClass;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.to.RestaurantTo;

@UtilityClass
public class ToUtil {

    public static Restaurant createFromRestaurantTo (RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName());
    }

    public static Restaurant updateFromRestaurantTo (Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }
}
