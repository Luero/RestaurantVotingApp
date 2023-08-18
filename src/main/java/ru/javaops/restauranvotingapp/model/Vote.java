package ru.javaops.restauranvotingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "voting_date"}, name = "vote_unique_user_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "voting_date", nullable = false)
    @NotNull
    private LocalDate votingDate;

    @Column(name = "voting_time", nullable = false)
    @NotNull
    private LocalTime votingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote(Integer id, User user, LocalDate votingDate, LocalTime votingTime, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.votingDate = votingDate;
        this.votingTime = votingTime;
        this.restaurant = restaurant;
    }

    public Vote(User user, LocalDate votingDate, LocalTime votingTime, Restaurant restaurant) {
        this.user = user;
        this.votingDate = votingDate;
        this.votingTime = votingTime;
        this.restaurant = restaurant;
    }
}
