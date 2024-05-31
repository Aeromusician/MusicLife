package ru.naumov.musiclife.userprofile;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileDTO {

    private Long id;

    private String bio;

    private String gear;

    private String isDeleted;

    private List<Long> myEvent;

    private List<Long> answeredEvents;
}
