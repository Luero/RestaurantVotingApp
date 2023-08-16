package ru.javaops.restauranvotingapp.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.service.MenuService;

import java.net.URI;

import static ru.javaops.restauranvotingapp.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishesController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class AdminDishesController {

    static final String REST_URL = "/api/admin/restaurant/{id}/dishes";

    @Autowired
    private final RestaurantRepository repository;

    @Autowired
    private final MenuService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> addToMenuWithLocation(@PathVariable int id, @Valid @RequestBody Dish dish) {
        log.info("update menu for restaurant with id={}", id);
        checkNew(dish);
        Dish created = service.save(id, dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(id, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
