package ru.naumov.musiclife.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String userName);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUserType(UserType userType);

    Optional<User> findUserById(Long id);

    Boolean existsUserByUsername(String userName);

    Boolean existsUserByEmail(String email);
}
