package ru.naumov.musiclife.userprofile;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.naumov.musiclife.auth.User;
import ru.naumov.musiclife.auth.UserRepository;
import ru.naumov.musiclife.event.EventInUserProfileDTO;
import ru.naumov.musiclife.event.EventService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final EventService eventService;

    @Override
    @Transactional
    public void createProfileFirst(String userName) {
        User user = userRepository.findUserByUsername(userName).orElseThrow(() ->
                new EntityNotFoundException(String.format("Пользователь с Именем: %s, не найден", userName)));
        UserProfileEntity userProfile = new UserProfileEntity();
        userProfile.setUser(user);
        profileRepository.save(userProfile);
    }

    @Override
    @Transactional
    public void addBioAndGear(UserProfileDTO dto) {
        UserProfileEntity userProfile = profileRepository.findById(dto.getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Профиль с ID: %d, не найден", dto.getId())));

        if (StringUtils.isNotBlank(dto.getBio())) {
            userProfile.setBio(dto.getBio());
        }
        if (StringUtils.isNotBlank(dto.getGear())) {
            userProfile.setGear(dto.getGear());
        }
        profileRepository.save(userProfile);
    }

    @Override
    @Transactional
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public UserProfileDTO getMyProfile(Long id) {
        return profileRepository.findById(id).map(this::toDto).orElseThrow(() -> new EntityNotFoundException("profile not found"));
    }

    @Override //todo:Добавить ответ на мероприятие
    public void answerEvent(Long eventId) {

    }

    private UserProfileDTO toDto(UserProfileEntity entity) {
        UserProfileDTO dto = new UserProfileDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getGear() != null) {
            dto.setGear(entity.getGear());
        }

        if (entity.getBio() != null) {
            dto.setBio(entity.getBio());
        }

        if (!CollectionUtils.isEmpty(entity.getAnsweredEvents())) {
            List<EventInUserProfileDTO> list = new ArrayList<>();
            entity.getAnsweredEvents().forEach(e -> {
                        EventInUserProfileDTO event = new EventInUserProfileDTO();
                        event.setId(e.getId());
                        event.setName(e.getName());
                        list.add(event);
                    }
            );
            dto.setAnsweredEvents(list);
        }

        if (!CollectionUtils.isEmpty(entity.getMyEvents())) {
            List<EventInUserProfileDTO> list = new ArrayList<>();
            entity.getMyEvents().forEach(e -> {
                        EventInUserProfileDTO event = new EventInUserProfileDTO();
                        event.setId(e.getId());
                        event.setName(e.getName());
                        list.add(event);
                    }
            );
            dto.setMyEvent(list);
        }
        return dto;
    }

    private User findUserByName(String name) {
        return userRepository.findUserByUsername(name).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }
}
