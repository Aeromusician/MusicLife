package ru.naumov.musiclife.userprofile;

public interface UserProfileService {

    void createProfileFirst(String userName);

    void addBioAndGear(UserProfileDTO dto);

    void deleteProfile(Long id);

    UserProfileDTO getMyProfile(Long id);

    void answerEvent(Long eventId);
}
