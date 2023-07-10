package ru.javaops.restauranvotingapp.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.model.User;
import ru.javaops.restauranvotingapp.model.Vote;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.repository.VoteRepository;
import ru.javaops.restauranvotingapp.service.VoteService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    static final String REST_URL = "/api/user";

    private final Logger log = getLogger(getClass());

    @Autowired
    private VoteRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VoteService service;

    //TODO add authentification and check for auth User
    @GetMapping
    public List<Restaurant> getAllWithMenuForToday() {
        log.info("getAllWithMenu");
        return restaurantRepository.getAllWithMenuForToday();
    }

    @GetMapping("/{userId}/votes")
    public List<Vote> getVotesHistory(@PathVariable int userId) {
    //    int userId = user.id();
        log.info("getVotesHistory for user {}", userId);
        return repository.getWithVotes(userId);
    }

    @PostMapping
    public Vote makeVote(@Valid @RequestBody User user, @RequestParam int restaurantId) {
        int userId = user.id();
        log.info("voting for user {}", userId);
        Vote newVote = service.save(userId, restaurantId);
        return newVote;
    }
}
