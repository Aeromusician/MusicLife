package ru.naumov.musiclife.userprofile;

public interface UserProfileService {

    void addBioAndGear(UserProfileDTO dto);

    void deleteProfile(Long id);

    UserProfileDTO getMyProfile(Long id);

    void answerEvent(Long eventId);
}
