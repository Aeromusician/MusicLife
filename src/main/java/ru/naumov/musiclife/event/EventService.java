package ru.naumov.musiclife.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    Long createEvent(EventDTO dto, String userName);

    void deleteEvent(Long id, String userName) throws Exception;

    Long updateEvent(EventDTO dto);

    Page<EventDTO> getAllEvents(Pageable pageable);

    void updateMusiciansInEvent(Long musician, Long eventId);

    EventDTO getEvent(Long id);
}
