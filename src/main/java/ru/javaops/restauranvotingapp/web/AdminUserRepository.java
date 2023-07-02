package ru.javaops.restauranvotingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.restauranvotingapp.repository.UserRepository;

@RestController
@RequestMapping(value = AdminUserRepository.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserRepository {

    static final String REST_URL = "/api/admin/users";

    @Autowired
    protected UserRepository repository;
}
