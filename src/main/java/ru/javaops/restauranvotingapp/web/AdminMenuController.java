package ru.javaops.restauranvotingapp.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.model.Dish;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.service.*;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javaops.restauranvotingapp.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuController {

    static final String REST_URL = "/api/admin/menu";

    private final Logger log = getLogger(getClass());

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private MenuService service;

    @GetMapping("/{id}")
    public Restaurant getWithMenu(@PathVariable int id) {
        log.info("getWithMenu {}", id);
        return repository.getWithMenu(id).orElseThrow(()
                -> new RuntimeException("Entity with id=" + id + " not found"));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Dish addToMenu(@PathVariable int id, @Valid @RequestBody Dish dish) {
        log.info("update menu for restaurant with id={}", id);
        checkNew(dish);
        Dish created = service.save(id, dish);
        return created;
    }
}
