package ru.javaops.restauranvotingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaops.restauranvotingapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
