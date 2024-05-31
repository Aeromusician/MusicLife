package ru.naumov.musiclife.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.naumov.musiclife.Security.Response;
import ru.naumov.musiclife.event.EventDTO;
import ru.naumov.musiclife.event.EventService;
import ru.naumov.musiclife.userprofile.UserProfileDTO;
import ru.naumov.musiclife.userprofile.UserProfileService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secured")
public class MainController {

    private final EventService eventService;

    private final UserProfileService userProfileService;


    @GetMapping("/user")
    public String userAccess(Principal principal) {
        if (principal == null) {
            return null;
        }
        return principal.getName();
    }

    @PostMapping("/create-event")
    public Response<Long> createEvent(@RequestBody EventDTO createDto) {
        return Response.data(eventService.createEvent(createDto));
    }

    @DeleteMapping("/delete-event/{id}")
    public Response<String> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return Response.OK;
    }

    @PutMapping("/update-event")
    public Response<Long> updateEvent(@RequestBody EventDTO eventDTO) {
        return Response.data(eventService.updateEvent(eventDTO));
    }

    @PostMapping("/add-bio")
    public Response<String> addBioAndGear(@RequestBody UserProfileDTO dto) {
        userProfileService.addBioAndGear(dto);
        return Response.OK;
    }

    @DeleteMapping("/delete-profile/{}")
    public Response<String> deleteProfile(Long id) {
        userProfileService.deleteProfile(id);
        return Response.OK;
    }

    @GetMapping("/{id}")
    public Response<UserProfileDTO> getMyProfile(@PathVariable Long id) {
        userProfileService.getMyProfile(id);
        return Response.data(userProfileService.getMyProfile(id));
    }

    @PostMapping("/answer-event/{eventId}")
    public Response<String> answerEvent(@PathVariable Long eventId) {
        userProfileService.answerEvent(eventId);
        return Response.OK;
    }
}
