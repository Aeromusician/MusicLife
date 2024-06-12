package ru.naumov.musiclife.event;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.naumov.musiclife.auth.User;
import ru.naumov.musiclife.auth.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;
    private final UserRepository userRepository;

    @Override
    public Long createEvent(EventDTO dto, String userName) {
        EventEntity entity = new EventEntity();
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setCost(dto.getCost());
        entity.setOrganizerId(findUserByName(userName));
        return null;
    }

    private User findUserByName(String name) {
        return userRepository.findUserByUsername(name).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    @Override
    public void deleteEvent(Long id, String userName) throws Exception {
        EventEntity entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Мероприятие не найдено"));
        User user = userRepository.findUserByUsername(userName).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        if (entity.getOrganizerId().getId().equals(user.getId())) {
            repository.delete(entity);
        } else {
            throw new Exception("Вы не можете удалить чужое мероприятие!");
        }
    }

    @Override //todo:Допилить остальные поля
    public Long updateEvent(EventDTO dto) {
        EventEntity entity = repository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Мероприятие не найдено"));
        if (!entity.getCost().equals(dto.getCost()) && dto.getCost() != null) {
            entity.setCost(dto.getCost());
        }
        if (!entity.getName().equals(dto.getName()) && dto.getName() != null) {
            entity.setName(dto.getName());
        }
        return repository.save(entity).getId();
    }

    @Override
    public List<EventDTO> getAllEvents(Sort sort) { //todo:Добавить фильтрацию
        List<EventEntity> event = repository.findAll(sort);
        List<EventDTO> dto = new ArrayList<>();
        event.forEach(e -> {
            dto.add(toDto(e));
        });
        return dto;
    }

    @Override
    public EventDTO getEvent(Long id) {
        return repository.findById(id).map(this::toDto).orElseThrow(() -> new EntityNotFoundException("Мероприятие не найдено"));
    }

    //todo:Допилить метод toDTO у Event
    private EventDTO toDto(EventEntity entity) {
        return new EventDTO();
    }
}
