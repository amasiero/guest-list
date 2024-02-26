package me.amasiero.guestlist.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.amasiero.guestlist.data.entity.GuestEntity;

@Repository
public interface GuestJpaRepository extends JpaRepository<GuestEntity, Long> {
    Optional<GuestEntity> findByName(String name);
}
