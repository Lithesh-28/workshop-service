package com.ivoyant.workshop_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mechanics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Mechanic name is required")
    @Size(max = 50, message = "Mechanic name must be less than 50 characters")
    private String name;

    @NotBlank(message = "Expertise is required")
    @Size(max = 100, message = "Expertise must be less than 100 characters")
    private String expertise;
}