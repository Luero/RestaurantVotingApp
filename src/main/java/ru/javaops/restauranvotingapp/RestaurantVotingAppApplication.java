package ru.javaops.restauranvotingapp;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javaops.restauranvotingapp.repository.UserRepository;

@SpringBootApplication
@AllArgsConstructor
public class RestaurantVotingAppApplication {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingAppApplication.class, args);
    }
}
