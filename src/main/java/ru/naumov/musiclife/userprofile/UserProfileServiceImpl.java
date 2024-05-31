package ru.naumov.musiclife.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public void addBioAndGear(UserProfileDTO dto) {

    }

    @Override
    public void deleteProfile(Long id) {

    }

    @Override
    public UserProfileDTO getMyProfile(Long id) {
        return null;
    }

    @Override
    public void answerEvent(Long eventId) {

    }
}
