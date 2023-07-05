package ru.javaops.restauranvotingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDate registered;

    //TODO find a way to achieve result without JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Dish> menu;

    public Restaurant(Integer id, String name, LocalDate registered) {
        super(id);
        this.name = name;
        this.registered = registered;
    }
}
