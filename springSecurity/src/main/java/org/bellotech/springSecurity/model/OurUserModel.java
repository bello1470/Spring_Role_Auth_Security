package org.bellotech.springSecurity.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name ="ourusers" )
@Entity
public class OurUserModel {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String password;

    private String roles;

}
