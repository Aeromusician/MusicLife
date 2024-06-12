package ru.naumov.musiclife.userprofile;

import lombok.Data;
import ru.naumov.musiclife.event.EventInUserProfileDTO;

import java.util.List;

@Data
public class UserProfileDTO {

    private Long id;

    private String bio;

    private String gear;

    private String isDeleted;

    private List<EventInUserProfileDTO> myEvent;

    private List<EventInUserProfileDTO> answeredEvents;
}
