package com.ivoyant.workshop_service.service;

import com.ivoyant.workshop_service.entity.Mechanic;
import com.ivoyant.workshop_service.entity.Slot;
import com.ivoyant.workshop_service.exception.MechanicNotFoundException;
import com.ivoyant.workshop_service.exception.SlotNotFoundException;
import com.ivoyant.workshop_service.repository.MechanicRepository;
import com.ivoyant.workshop_service.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    public List<Slot> getAvailableSlots(LocalDate date) {
        List<Slot> slots = slotRepository.findByAvailableTrueAndDate(date);
        if (slots.isEmpty()) {
            throw new SlotNotFoundException("No available slots found for date " + date);
        }
        return slots;
    }

    public Slot createSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    public Slot updateSlot(Integer id, Slot slotDetails) {
        Slot slot = slotRepository.findById(id)
                .orElseThrow(() -> new SlotNotFoundException("Slot not found with id " + id));

        slot.setDate(slotDetails.getDate());
        slot.setTime(slotDetails.getTime());
        slot.setAvailable(slotDetails.isAvailable());
        slot.setAssignedBookingId(slotDetails.getAssignedBookingId());

        return slotRepository.save(slot);
    }

    public void updateSlotStatus(Integer bookingId, String status) {
        Slot slot = slotRepository.findByAssignedBookingId(bookingId)
                .orElseThrow(() -> new SlotNotFoundException("Slot not found for booking id " + bookingId));

        if(status.equalsIgnoreCase("COMPLETED")) {
            slot.setAvailable(false);
        }
        slotRepository.save(slot);
    }

    public Slot assignMechanicToSlot(Integer slotId, Integer mechanicId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new SlotNotFoundException("Slot not found with id " + slotId));
        Mechanic mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() -> new MechanicNotFoundException("Mechanic not found with id " + mechanicId));

        slot.setMechanic(mechanic);
        return slotRepository.save(slot);
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }
}
