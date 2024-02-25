package me.amasiero.guestlist.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.amasiero.guestlist.data.entity.EventEntity;

@Repository
public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {
}
