package ru.javaops.restauranvotingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.user.id=?1")
    List<Vote> getWithRestaurant(int userId);

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant WHERE v.votingDate=?1 AND v.user.id=?2")
    Optional<Vote> getWithRestaurantByDate(LocalDate date, int userId);

    @Query("SELECT v FROM Vote v WHERE v.votingDate=?1 AND v.user.id=?2")
    Optional<Vote> getByDate(LocalDate date, int userId);
}
