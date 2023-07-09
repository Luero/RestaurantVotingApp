package ru.javaops.restauranvotingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.repository.UserRepository;
import ru.javaops.restauranvotingapp.model.Vote;
import ru.javaops.restauranvotingapp.repository.VoteRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private UserRepository userRepository;

    private VoteRepository repository;

    @Transactional
    public Vote save(int userId, Vote vote) {
        Optional<Vote> todayVote = repository.getTodayVote(userId);
        if (todayVote.isEmpty()) {
            vote.setUser(userRepository.getExisted(userId));
            return repository.save(vote);
        } else {
            Vote existedVote = todayVote.get();
            if(existedVote.getTime().isBefore(LocalTime.of(11, 0))) {
                existedVote.setRestaurant(vote.getRestaurant());
                return repository.save(existedVote);
            } else {
                throw new RuntimeException("You cannot update your vote for today");
            }
        }
    }
}
