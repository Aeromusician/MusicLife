package ru.naumov.musiclife.event;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.naumov.musiclife.auth.User;
import ru.naumov.musiclife.auth.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return repository.save(entity).getId();
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

    public void updateMusiciansInEvent(Long musician, Long eventId) {
        Optional<EventEntity> event = repository.findById(eventId);
        if (event.isPresent()) {
            Optional<User> userById = userRepository.findUserById(musician);
            if (userById.isPresent()) {
                EventEntity entity = event.get();
                List<User> musicians = entity.getMusicians();
                if (CollectionUtils.isEmpty(musicians)) {
                    List<User> list = new ArrayList<>();
                    list.add(userById.get());
                    entity.setMusicians(list);
                    repository.save(entity);
                } else {
                    musicians.add(userById.get());
                    repository.save(entity);
                }
            }
        }
    }

    @Override
    public Page<EventDTO> getAllEvents(Pageable pageable) { //todo:Добавить фильтрацию
        return repository.findAll(pageable).map(this::toDto);
    }

    @Override
    public EventDTO getEvent(Long id) {
        return repository.findById(id).map(this::toDto).orElseThrow(() -> new EntityNotFoundException("Мероприятие не найдено"));
    }

    private EventDTO toDto(EventEntity entity) {
        EventDTO dto = new EventDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getCost() != null) {
            dto.setCost(entity.getCost());
        }

        if (entity.getName() != null) {
            dto.setName(entity.getName());
        }
        if (entity.getLocation() != null) {

            dto.setLocation(entity.getLocation());
        }
        if (!CollectionUtils.isEmpty(entity.getMusicians())) {
            List<Long> dtoList = new ArrayList<>();
            List<User> musicians = entity.getMusicians();
            musicians.forEach(e -> {
                dtoList.add(e.getId());
            });
            dto.setMusicians(dtoList);
        }
        if (entity.getOrganizerId() != null) {
            dto.setOrganizerId(entity.getOrganizerId().getId());
        }
        return dto;
    }
}
