package ru.naumov.musiclife.event;

import lombok.Data;

@Data
public class EventInUserProfileDTO {

    private Long id;

    private String name;

    private Long cost;

    private String location;

    private Long orgId;

}
