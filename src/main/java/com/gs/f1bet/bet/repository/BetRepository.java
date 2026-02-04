package com.gs.f1bet.bet.repository;

import com.gs.f1bet.bet.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    Collection<Bet> findByEventKey(String eventKey);
}
