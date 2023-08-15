package ru.javaops.restauranvotingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.error.IllegalRequestDataException;
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
    public Vote save(int userId, int restaurantId) {
        Optional<Vote> todayVoteOptional = repository.getVoteByDate(LocalDate.now(), userId);
        LocalTime currentTime = LocalTime.now();
        if (todayVoteOptional.isEmpty()) {
            Vote vote = new Vote(null, userRepository.getExisted(userId), LocalDate.now(), LocalTime.now(),
                    restaurantRepository.getExisted(restaurantId));
            return repository.save(vote);
        } else {
            if (currentTime.isBefore(VOTING_DEADLINE)) {
                Vote todayVote = todayVoteOptional.get();
                todayVote.setRestaurant(restaurantRepository.getExisted(restaurantId));
                return repository.save(todayVote);
            } else {
                throw new IllegalRequestDataException("You cannot update your vote for today");
            }
        }
    }
}
