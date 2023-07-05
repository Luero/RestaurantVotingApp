package ru.javaops.restauranvotingapp.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.restauranvotingapp.model.User;
import ru.javaops.restauranvotingapp.repository.UserRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javaops.restauranvotingapp.util.ValidationUtil.assureIdConsistent;
import static ru.javaops.restauranvotingapp.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {

    static final String REST_URL = "/api/admin/users";

    private final Logger log = getLogger(getClass());

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        log.info("get {}", id);
        return repository.getExisted(id);
    }

/*    @GetMapping("/{id}/with-votes")
    public User getWithVotes(@PathVariable int id) {
        log.info("getWithVotes {}", id);
        return repository.getWithVotes(id).orElseThrow(()
                -> new RuntimeException("Entity with id=" + id + " not found"));
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@Valid @RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        User created = repository.prepareAndSave(user);
        return created;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        repository.prepareAndSave(user);
    }
}
