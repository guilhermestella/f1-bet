package com.gs.f1bet.event.repository;

import com.gs.f1bet.event.model.EventOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOutcomeRepository extends JpaRepository<EventOutcome, Long> {
}
