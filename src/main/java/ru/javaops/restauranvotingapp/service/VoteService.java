package ru.javaops.restauranvotingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.Restaurant;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.repository.UserRepository;
import ru.javaops.restauranvotingapp.model.Vote;
import ru.javaops.restauranvotingapp.repository.VoteRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    private VoteRepository repository;

    @Transactional
    public Vote save(int userId, int restaurantId) {
        Optional<Vote> todayVote = repository.getTodayVote(userId);
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        if (todayVote.isEmpty()) {
            Vote vote = new Vote(null, userRepository.getExisted(userId), LocalDateTime.now(), restaurant);
            return repository.save(vote);
        } else {
            Vote existedVote = todayVote.get();
            if(existedVote.getTime().isBefore(LocalTime.of(11, 0))) {
                existedVote.setRestaurant(restaurant);
                return repository.save(existedVote);
            } else {
                throw new RuntimeException("You cannot update your vote for today");
            }
        }
    }
}
