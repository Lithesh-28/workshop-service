package com.ivoyant.workshop_service.repository;

import com.ivoyant.workshop_service.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {
    List<Slot> findByAvailableTrueAndDate(LocalDate date);
    Optional<Slot> findByAssignedBookingId(Integer bookingId);
}
