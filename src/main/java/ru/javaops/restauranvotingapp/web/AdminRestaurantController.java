package ru.javaops.restauranvotingapp.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.service.RestaurantService;
import ru.javaops.restauranvotingapp.to.RestaurantTo;
import ru.javaops.restauranvotingapp.util.ToUtil;

import java.util.List;

import static ru.javaops.restauranvotingapp.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class AdminRestaurantController {

    static final String REST_URL = "/api/admin/restaurants";

    @Autowired
    private final RestaurantRepository repository;

    @Autowired
    private final RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get {}", id);
        return repository.getExisted(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "menu_for_date", allEntries = true)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "menu_for_date", allEntries = true)
    public Restaurant create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        checkNew(restaurantTo);
        Restaurant created = repository.save(ToUtil.createFromRestaurantTo(restaurantTo));
        return created;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "menu_for_date", allEntries = true)
    public void update(@PathVariable int id, @Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("update restaurant with id={}", id);
        service.update(id, restaurantTo);
    }
}
