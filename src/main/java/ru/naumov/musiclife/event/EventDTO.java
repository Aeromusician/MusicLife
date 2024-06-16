package ru.naumov.musiclife.event;

import lombok.Data;

import java.util.List;

@Data
public class EventDTO {

    private Long id;

    private String name;

    private String location;

    private Long cost;

    private List<Long> musicians;

    private Long organizerId;

    private String orgMail;

    private String orgUserName;
}
