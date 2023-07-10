package ru.javaops.restauranvotingapp.util;

import lombok.experimental.UtilityClass;
import ru.javaops.restauranvotingapp.model.User;
import ru.javaops.restauranvotingapp.to.UserTo;

@UtilityClass
public class ToUtil {

    public static UserTo transferUserToUserTo (User user) {
        return new UserTo(user.id(), user.getName(), user.getEmail(), user.getRoles());
    }
}
