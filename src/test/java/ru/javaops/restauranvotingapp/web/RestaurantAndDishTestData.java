package ru.javaops.restauranvotingapp.web;

import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.model.Restaurant;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantAndDishTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class,
            "registered", "menu");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_MENU_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "menu").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class,
            "restaurant", "menuDate");

    public static final int BINGO_DISH_ID = 2;

    public static final Restaurant bingoDish = new Restaurant(BINGO_DISH_ID, "Bingo Dish");

    public static Restaurant getUpdated() {
        return new Restaurant(BINGO_DISH_ID, "newName");
    }
    public static Dish getNew() {
        return new Dish("noodles", 12.0);
    }
}
