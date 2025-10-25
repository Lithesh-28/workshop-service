package com.ivoyant.workshop_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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


    @NotNull(message = "Slot date is required")
    @FutureOrPresent(message = "Slot date cannot be in the past")
    private LocalDate date;
    @NotNull(message = "Slot time is required")
    @JsonFormat(pattern = "HH:mm") // ensure correct JSON serialization
    private LocalTime time;

    @NotNull(message = "Availability must be provided")
    private Boolean available;

    private Integer assignedBookingId; // optional, can be null initially

    @ManyToOne(optional = true)
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;
}

