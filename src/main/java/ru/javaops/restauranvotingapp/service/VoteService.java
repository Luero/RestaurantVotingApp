package ru.javaops.restauranvotingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.error.MethodNotAllowedException;
import ru.javaops.restauranvotingapp.repository.RestaurantRepository;
import ru.javaops.restauranvotingapp.repository.UserRepository;
import ru.javaops.restauranvotingapp.model.Vote;
import ru.javaops.restauranvotingapp.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final LocalTime VOTING_DEADLINE = LocalTime.of(11, 0);

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    private VoteRepository repository;

    @Transactional
    public Vote make(int userId, int restaurantId) {
        Optional<Vote> todayVoteOptional = repository.getByDate(LocalDate.now(), userId);
        if (todayVoteOptional.isEmpty()) {
            Vote vote = new Vote(null, userRepository.getExisted(userId), LocalDate.now(), LocalTime.now(),
                    restaurantRepository.getExisted(restaurantId));
            return repository.save(vote);
        } else {
            throw new MethodNotAllowedException("You have already voted today, please, try the update function.");
        }
    }

    @Transactional
    public Vote update(int userId, int restaurantId) {
        Optional<Vote> todayVoteOptional = repository.getByDate(LocalDate.now(), userId);
        if (todayVoteOptional.isPresent()) {
            LocalTime currentTime = LocalTime.now();
            if (currentTime.isBefore(VOTING_DEADLINE)) {
                Vote todayVote = todayVoteOptional.get();
                todayVote.setRestaurant(restaurantRepository.getExisted(restaurantId));
                return repository.save(todayVote);
            } else {
                throw new MethodNotAllowedException("You cannot update your vote for today after 11:00 am.");
            }
        } else {
            throw new MethodNotAllowedException("You have not voted today. Please, make a vote.");
        }
    }
}

