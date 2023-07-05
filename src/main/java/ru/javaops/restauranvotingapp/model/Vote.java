package ru.javaops.restauranvotingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "voting_date"}, name = "vote_unique_user_datetime_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "voting_date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime votingDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Vote(Integer id, User user, LocalDateTime votingDateTime, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.votingDateTime = votingDateTime;
        this.restaurant = restaurant;
    }

    @Schema(hidden = true)
    public LocalDate getDate() {
        return votingDateTime.toLocalDate();
    }

    @Schema(hidden = true)
    public LocalTime getTime() {
        return votingDateTime.toLocalTime();
    }
}
