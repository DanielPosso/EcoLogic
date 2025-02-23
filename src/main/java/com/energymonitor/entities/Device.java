package com.energymonitor.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}