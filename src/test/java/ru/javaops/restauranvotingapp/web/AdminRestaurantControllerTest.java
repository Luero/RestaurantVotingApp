package ru.javaops.restauranvotingapp.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.service.RestaurantService;
import ru.javaops.restauranvotingapp.util.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.restauranvotingapp.web.RestaurantAndDishTestData.*;
import static ru.javaops.restauranvotingapp.web.UserTestData.ADMIN_MAIL;

public class AdminRestaurantControllerTest extends AbstractControllerTest {

    static final String REST_URL = "/api/admin/restaurants";

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private RestaurantService service;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL+ "/" + BINGO_DISH_ID))
                .andExpect(status().isNoContent());
        assertFalse(repository.findById(BINGO_DISH_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    public void update() throws Exception {
        Restaurant updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL+ "/" + BINGO_DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(repository.getExisted(BINGO_DISH_ID), updated);
    }
}
