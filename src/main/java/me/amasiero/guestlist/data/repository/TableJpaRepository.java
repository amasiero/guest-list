package me.amasiero.guestlist.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import me.amasiero.guestlist.data.entity.TableEntity;

@Repository
public interface TableJpaRepository extends JpaRepository<TableEntity, Long> {
    @Query(value = "select count(*) > 0 from tables t where t.status = 0", nativeQuery = true)
    Boolean hasTableAvailable();
}
