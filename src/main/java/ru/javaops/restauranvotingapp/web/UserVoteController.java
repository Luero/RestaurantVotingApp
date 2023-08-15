package ru.javaops.restauranvotingapp.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restauranvotingapp.AuthUser;
import ru.javaops.restauranvotingapp.model.Vote;
import ru.javaops.restauranvotingapp.repository.VoteRepository;
import ru.javaops.restauranvotingapp.service.VoteService;

import java.util.List;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserVoteController {

    static final String REST_URL = "/api/user/votes";

    @Autowired
    private final VoteRepository repository;

    @Autowired
    private final VoteService service;

    @GetMapping
    @Cacheable(cacheNames = "votes")
    public List<Vote> getVotesHistory(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getVotesHistory for user {}", authUser.id());
        return repository.getWithRestaurant(authUser.id());
    }

    @PostMapping
    @CacheEvict(value = "votes", allEntries = true)
    public Vote makeVote(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        int userId = authUser.id();
        log.info("voting for user {}", userId);
        Vote newVote = service.save(userId, restaurantId);
        return newVote;
    }
}
