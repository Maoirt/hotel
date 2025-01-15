package com.example.hotel.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String midName;
    private String passport;
    private Timestamp checkIn;
    private Timestamp eviction;

    @ManyToOne
    @JoinColumn(name = "room_number")
    private Room room;
}
