//EnergyConsumption entity - Stores energy usage data
//Tracks consumption details, timestamps, and associated costs
package com.energymonitor.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "energy_consumption")
public class EnergyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Establishes relationship with User entity (Many consumptions can belong to one user)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double consumption;  //Energy usage in kWh

    @Column(nullable = false)
    private LocalDateTime timestamp;  //When the consumption was recorded

    private String deviceType;  //Type of device consuming energy

    private String location;    //Where in the house

    private Double cost;        //Monetary cost of consumption

}
