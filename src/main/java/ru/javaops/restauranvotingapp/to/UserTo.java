package ru.javaops.restauranvotingapp.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javaops.restauranvotingapp.model.Role;

import java.util.Set;

@NoArgsConstructor
@Data
public class UserTo {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // https://stackoverflow.com/a/28025008/548473
    private Integer id;

    private String name;

    private String email;

    private Set<Role> roles;

    public UserTo (Integer id, String name, String email, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserTo:" + id + '[' + email + ']';
    }
}
