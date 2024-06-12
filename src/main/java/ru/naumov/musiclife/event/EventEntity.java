package ru.naumov.musiclife.event;

import jakarta.persistence.*;
import lombok.Data;
import ru.naumov.musiclife.auth.User;

import java.util.List;

@Entity
@Table(name = "event")
@Data
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private Long cost;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private User organizerId;

    @Column
    @ManyToMany
    private List<User> musicians;

}
