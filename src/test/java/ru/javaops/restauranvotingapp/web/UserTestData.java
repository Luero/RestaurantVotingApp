package ru.javaops.restauranvotingapp.web;

import ru.javaops.restauranvotingapp.model.Role;
import ru.javaops.restauranvotingapp.model.User;

public class UserTestData {

    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class,
            "votes", "password");

    public static final String ADMIN_MAIL = "admin@yandex.ru";
    public static final String USER_MAIL = "user@yandex.ru";
    public static final String VICTOR_MAIL = "victor@yandex.ru";

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int VICTOR_ID = 3;

    public static final int NOT_FOUND = 50;

    public static final User user = new User(USER_ID, "John", "user@yandex.ru", "password", Role.USER);

    public static final User admin = new User(ADMIN_ID, "Kate", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);
    public static final User victor = new User(VICTOR_ID, "Victor", "victor@yandex.ru", "victor", Role.USER);
}
