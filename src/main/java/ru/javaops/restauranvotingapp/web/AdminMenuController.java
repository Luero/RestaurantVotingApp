package ru.javaops.restauranvotingapp.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restauranvotingapp.error.NotFoundException;
import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.service.MenuService;

import static ru.javaops.restauranvotingapp.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class AdminMenuController {

    static final String REST_URL = "/api/admin/restaurants";

    @Autowired
    private final RestaurantRepository repository;

    @Autowired
    private final MenuService service;

    @GetMapping("/{id}/menu")
    public Restaurant getWithMenu(@PathVariable int id) {
        log.info("getWithMenu {}", id);
        return repository.getWithMenu(id).orElseThrow(()
                -> new NotFoundException("Entity with id=" + id + " not found"));
    }

    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    //@CachePut("dishes")
    public Dish addToMenu(@PathVariable int id, @Valid @RequestBody Dish dish) {
        log.info("update menu for restaurant with id={}", id);
        checkNew(dish);
        Dish created = service.save(id, dish);
        return created;
    }
}
