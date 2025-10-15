package com.ivoyant.workshop_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;
    private LocalTime time;
    private boolean available;
    private Integer assignedBookingId;

    @ManyToOne
    @JoinColumn(name = "mechanic_id") // foreign key column in slots table
    private Mechanic mechanic;

}

