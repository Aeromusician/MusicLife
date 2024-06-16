package ru.naumov.musiclife.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface EventService {

    Long createEvent(EventDTO dto, String userName);

    void deleteEvent(Long id, String userName) throws Exception;

    Long updateEvent(EventDTO dto);

    Page<EventDTO> getAllEvents(Pageable pageable);

    List<EventDTO> getMyEvents(Principal principal);

    void updateMusiciansInEvent(Long musician, Long eventId);

    EventDTO getEvent(Long id);
}
