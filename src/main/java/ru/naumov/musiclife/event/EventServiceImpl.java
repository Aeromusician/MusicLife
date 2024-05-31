package ru.naumov.musiclife.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    public Long createEvent(EventDTO dto) {
        return null;
    }

    @Override
    public void deleteEvent(Long id) {

    }

    @Override
    public Long updateEvent(EventDTO dto) {
        return null;
    }
}
