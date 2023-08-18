package ru.javaops.restauranvotingapp.web;

import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.model.Restaurant;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantAndDishTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class,
            "registered", "dishes");
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_MENU_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "dishes.restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class,
            "restaurant");

    public static final int FAST_FOOD_M_ID = 1;
    public static final int BINGO_DISH_ID = 2;

    public static final Restaurant fastFoodM = new Restaurant(FAST_FOOD_M_ID, "Fast food M");
    public static final Restaurant bingoDish = new Restaurant(BINGO_DISH_ID, "Bingo Dish");

    public static final int DISH9_ID = 9;

    public static final Dish DISH9 = new Dish(DISH9_ID, "Cheese soup", 1500, fastFoodM,
            LocalDate.of(2023, Month.JUNE, 20));

    public static final Dish DISH10 = new Dish(DISH9_ID + 1, "Cheeseburger", 2100, fastFoodM,
            LocalDate.of(2023, Month.JUNE, 20));

    public static final Dish DISH11 = new Dish(DISH9_ID + 2, "Donuts with coffee", 1700, fastFoodM,
            LocalDate.of(2023, Month.JUNE, 21));

    public static final Dish DISH12 = new Dish(DISH9_ID + 3, "Fish and chips", 2400, fastFoodM,
            LocalDate.of(2023, Month.JUNE, 21));

    static {
        fastFoodM.setDishes(List.of(DISH11, DISH12, DISH9, DISH10));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(FAST_FOOD_M_ID, "newName");
    }
    public static Dish getNew() {
        return new Dish("noodles", 1200, LocalDate.now());
    }
}
