package ru.naumov.musiclife.userprofile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    @Query("select e from UserProfileEntity e where e.user.id = :userId")
    UserProfileEntity findByUserId(Long userId);
}
