package ru.javaops.restauranvotingapp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.User;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {

/*    @Query("SELECT u FROM User u LEFT JOIN FETCH u.votes v LEFT JOIN FETCH v.restaurant WHERE u.id=?1")
    Optional<User> getWithVotes(int id);*/

    //TODO encode user password (through SecurityConfig)
    @Transactional
    default User prepareAndSave(User user) {
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail().toLowerCase());
        return save(user);
    }
}
