package ru.javaops.restauranvotingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.User;
import ru.javaops.restauranvotingapp.model.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.user.id=?1")
    List<Vote> getWithVotes(int userId);

    @Query("SELECT v FROM Vote v WHERE TIMESTAMPDIFF(DAY, v.votingDateTime, NOW()) = 0 AND v.user.id=?1")
    Optional<Vote> getTodayVote(int userId);
}
