package ru.javaops.restauranvotingapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.restauranvotingapp.model.User;

import java.util.Optional;

import static ru.javaops.restauranvotingapp.config.SecurityConfig.PASSWORD_ENCODER;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.votes v LEFT JOIN FETCH v.restaurant WHERE u.id=?1")
    Optional<User> getWithVotes(int id);

    @Transactional
    default User prepareAndSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return save(user);
    }

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);
}
