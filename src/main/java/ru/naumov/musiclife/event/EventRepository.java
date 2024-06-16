package ru.naumov.musiclife.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("select e from EventEntity e where e.organizerId.id = :userId")
    List<EventEntity> getUserEvents(Long userId);

}
