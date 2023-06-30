package ru.javaops.restauranvotingapp;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javaops.restauranvotingapp.model.Role;
import ru.javaops.restauranvotingapp.model.User;
import ru.javaops.restauranvotingapp.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
public class RestaurantVotingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingAppApplication.class, args);
    }
}
