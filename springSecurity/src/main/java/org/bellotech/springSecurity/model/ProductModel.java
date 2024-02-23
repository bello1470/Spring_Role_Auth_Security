package org.bellotech.springSecurity.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "products")

public class ProductModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

}
