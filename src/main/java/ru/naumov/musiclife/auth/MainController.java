package ru.naumov.musiclife.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.naumov.musiclife.Security.Response;
import ru.naumov.musiclife.event.EventDTO;
import ru.naumov.musiclife.event.EventService;
import ru.naumov.musiclife.userprofile.UserProfileDTO;
import ru.naumov.musiclife.userprofile.UserProfileService;

import java.security.Principal;
import java.util.List;

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
    public Response<Long> createEvent(@RequestBody EventDTO createDto, Principal principal) {
        return Response.data(eventService.createEvent(createDto, principal.getName()));
    }

    @PostMapping("/get-all-events")
    public Response<List<EventDTO>> getAllEvents(@RequestBody Sort sort) {
        return Response.data(eventService.getAllEvents(sort));
    }

    @DeleteMapping("/delete-event/{id}")
    public Response<String> deleteEvent(@PathVariable("id") Long id, Principal principal) throws Exception {
        eventService.deleteEvent(id, principal.getName());
        return Response.OK;
    }

    @PutMapping("/update-event")
    public Response<Long> updateEvent(@RequestBody EventDTO eventDTO, Principal principal) {
        return Response.data(eventService.updateEvent(eventDTO));
    }

    @PostMapping("/add-bio")
    public Response<String> addBioAndGear(@RequestBody UserProfileDTO dto, Principal principal) {
        userProfileService.addBioAndGear(dto);
        return Response.OK;
    }

    @DeleteMapping("/delete-profile/{id}")
    public Response<String> deleteProfile(@PathVariable Long id) {
        userProfileService.deleteProfile(id);
        return Response.OK;
    }

    @GetMapping("/{id}")
    public Response<UserProfileDTO> getMyProfile(@PathVariable Long id) {
        userProfileService.getMyProfile(id);
        return Response.data(userProfileService.getMyProfile(id));
    }

    @PostMapping("/answer-event/{eventId}")
    public Response<String> answerEvent(@PathVariable Long eventId, Principal principal) throws Exception {
        userProfileService.answerEvent(eventId, principal.getName());
        return Response.OK;
    }
}
