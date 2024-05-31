package ru.naumov.musiclife.event;

public interface EventService {

    Long createEvent(EventDTO dto);

    void deleteEvent(Long id);

    Long updateEvent(EventDTO dto);

}
