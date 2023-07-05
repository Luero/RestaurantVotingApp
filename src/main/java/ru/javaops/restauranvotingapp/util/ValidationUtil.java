package ru.javaops.restauranvotingapp.util;

import lombok.experimental.UtilityClass;
import ru.javaops.restauranvotingapp.HasId;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new RuntimeException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new RuntimeException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}
