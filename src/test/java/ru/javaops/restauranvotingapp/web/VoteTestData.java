package ru.javaops.restauranvotingapp.web;

import ru.javaops.restauranvotingapp.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.javaops.restauranvotingapp.web.RestaurantAndDishTestData.fastFoodM;
import static ru.javaops.restauranvotingapp.web.UserTestData.user;
import static ru.javaops.restauranvotingapp.web.UserTestData.victor;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class,
            "votingTime", "user", "restaurant.dishes", "restaurant.registered");

    public static final LocalTime VOTING_DEADLINE = LocalTime.of(11, 0);

    public static final int VOTE6_ID = 6;

    public static Vote getNew() {
        return new Vote(victor, LocalDate.now(), LocalTime.now(), fastFoodM);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE6_ID, user, LocalDate.now(), LocalTime.now(), fastFoodM);
    }
}
