package ru.naumov.musiclife.userprofile;

import jakarta.persistence.*;
import lombok.Data;
import ru.naumov.musiclife.auth.User;
import ru.naumov.musiclife.event.EventEntity;

import java.util.List;

@Entity
@Table(name = "user_profile")
@Data
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private String bio;

    @Column
    private String gear;

    @Column
    private String isDeleted;

    @Column
    @OneToMany(mappedBy = "id")
    private List<EventEntity> myEvents;

    @Column
    @OneToMany(mappedBy = "id")
    private List<EventEntity> answeredEvents;
}
