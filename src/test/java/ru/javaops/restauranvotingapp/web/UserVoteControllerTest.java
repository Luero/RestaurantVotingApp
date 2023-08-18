package ru.javaops.restauranvotingapp.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.restauranvotingapp.model.Vote;
import ru.javaops.restauranvotingapp.repository.VoteRepository;
import ru.javaops.restauranvotingapp.service.VoteService;
import ru.javaops.restauranvotingapp.util.JsonUtil;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.restauranvotingapp.web.RestaurantAndDishTestData.BINGO_DISH_ID;
import static ru.javaops.restauranvotingapp.web.RestaurantAndDishTestData.FAST_FOOD_M_ID;
import static ru.javaops.restauranvotingapp.web.UserTestData.USER_MAIL;
import static ru.javaops.restauranvotingapp.web.UserTestData.VICTOR_MAIL;
import static ru.javaops.restauranvotingapp.web.VoteTestData.*;

public class UserVoteControllerTest extends AbstractControllerTest {

    static final String REST_URL = "/api/user/votes";

    @Autowired
    private VoteRepository repository;

    @Autowired
    private VoteService service;

    @Test
    @WithUserDetails(value = VICTOR_MAIL)
    void createWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(FAST_FOOD_M_ID))
                .content(JsonUtil.writeValue(newVote)));

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(repository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateWithinDifferentTime() throws Exception {
        Vote updated = getUpdated();
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(VOTING_DEADLINE)) {
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", String.valueOf(FAST_FOOD_M_ID))
                    .content(JsonUtil.writeValue(updated)))
                    .andExpect(status().isNoContent());

            VOTE_MATCHER.assertMatch(repository.getExisted(VOTE6_ID), updated);
        } else {
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", String.valueOf(BINGO_DISH_ID))
                    .content(JsonUtil.writeValue(updated)))
                    .andExpect(status().isMethodNotAllowed());
        }
    }

    //not working: https://stackoverflow.com/questions/2001671/override-java-system-currenttimemillis-for-testing-time-sensitive-code,
    //https://www.baeldung.com/java-override-system-time
}
