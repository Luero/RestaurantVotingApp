package ru.javaops.restauranvotingapp.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserRestaurantController {

    static final String REST_URL = "/api/user/restaurants";

    @Autowired
    private final RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> getAllWithMenuForToday() {
        log.info("getAllWithMenu");
        return repository.getAllWithMenuByDate(LocalDate.now());
    }
}
