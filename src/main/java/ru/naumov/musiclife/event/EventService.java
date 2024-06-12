package ru.naumov.musiclife.event;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface EventService {

    Long createEvent(EventDTO dto, String userName);

    void deleteEvent(Long id, String userName) throws Exception;

    Long updateEvent(EventDTO dto);

    List<EventDTO> getAllEvents(Sort sort);

    EventDTO getEvent(Long id);
}
