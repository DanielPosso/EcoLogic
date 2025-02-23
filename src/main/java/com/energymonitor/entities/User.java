//User entity class - Represents a user in the system
//Stores basic user information and authentication details
package com.energymonitor.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data //Lombok annotation to create getters, setters, toString, etc.
@Entity //Marks this class as a JPA entity
@Table(name = "users") //Specifies the database table name
public class User {

    @Id //Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Specifies the primary key generation strategy
    private Long id;

    @Column(unique = true, nullable = false) // Specifies that this field must be unique, so Email must be unique and not null
    private String email;

    @Column(nullable = false)  // Password cannot be null
    private String password;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private boolean enabled = true;  // User account status

}
