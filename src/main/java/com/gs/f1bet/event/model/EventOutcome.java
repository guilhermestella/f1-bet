package com.gs.f1bet.event.model;

import com.gs.f1bet.bet.model.Bet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "winningBets", "losingBets"})
public class EventOutcome {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String eventKey;

    @Column(nullable = false)
    private String driverNumber;

    @OneToMany(orphanRemoval = true)
    private Collection<Bet> winningBets;

    @OneToMany(orphanRemoval = true)
    private Collection<Bet> losingBets;
}
