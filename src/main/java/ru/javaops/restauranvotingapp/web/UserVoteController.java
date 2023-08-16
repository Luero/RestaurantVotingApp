package ru.javaops.restauranvotingapp.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restauranvotingapp.AuthUser;
import ru.javaops.restauranvotingapp.model.Vote;
import ru.javaops.restauranvotingapp.repository.VoteRepository;
import ru.javaops.restauranvotingapp.service.VoteService;

import java.net.URI;
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
    public List<Vote> getVotesHistory(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getVotesHistory for user {}", authUser.id());
        return repository.getWithRestaurant(authUser.id());
    }

    @PostMapping
    public ResponseEntity<Vote> makeVoteWithLocation(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        int userId = authUser.id();
        log.info("voting for user {}", userId);
        Vote newVote = service.save(userId, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newVote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newVote);
    }
}
