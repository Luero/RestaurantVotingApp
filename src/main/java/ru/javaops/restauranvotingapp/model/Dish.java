package ru.javaops.restauranvotingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Entity
@Table(name = "dish",
        uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "menu_date", "name"},
                name = "restaurant_unique_menu_date_dish_idx"))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    @Range(min = 0)
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "menu_date", nullable = false, updatable = false)
    @NotNull
    private LocalDate menuDate = LocalDate.now();

    public Dish(String name, long price, LocalDate menuDate) {
        this.name = name;
        this.price = price;
        this.menuDate = menuDate;
    }

    public Dish(Integer id, String name, long price, Restaurant restaurant, LocalDate menuDate) {
        super(id);
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
        this.menuDate = menuDate;
    }
}
