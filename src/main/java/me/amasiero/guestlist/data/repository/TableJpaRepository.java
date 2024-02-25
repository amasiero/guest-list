package me.amasiero.guestlist.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.amasiero.guestlist.data.entity.TableEntity;

@Repository
public interface TableJpaRepository extends JpaRepository<TableEntity, Long> {
}
