package com.ivoyant.workshop_service.controller;


import com.ivoyant.workshop_service.entity.Slot;
import com.ivoyant.workshop_service.service.SlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/workshop")
@Slf4j
public class SlotController {

    @Autowired
    private SlotService slotService;

    // Get available slots for a date
    @GetMapping("/slots")
    public ResponseEntity<List<Slot>> getSlots(@RequestParam(required = false) String date) {
        if (date != null) {
            LocalDate requestedDate = LocalDate.parse(date);
            log.info("Fetching slots for date: {}", requestedDate);
            return ResponseEntity.ok(slotService.getAvailableSlots(requestedDate));
        } else {
            log.info("Fetching all available slots");
            return ResponseEntity.ok(slotService.getAllSlots());
        }
    }


    // Create a new slot
    @PostMapping("/slots")
    public Slot createSlot(@RequestBody Slot slot) {
        log.info("Creating a new slot...");
        Slot slot1 = slotService.createSlot(slot);
        log.info("slot created ..." + slot1.toString());
        return slot1;
    }

    // Update a slot by ID
    @PutMapping("/slots/{id}")
    public Slot updateSlot(@PathVariable Integer id, @RequestBody Slot slot) {
        log.info("Updating slots with id " + id);
        Slot slot1 = slotService.updateSlot(id, slot);
        log.info("Updated slots Successfully " + slot1.toString());
        return slot1;
    }

    // Update status of a slot by booking ID
    @PostMapping("/{bookingId}/status")
    public String updateStatus(@PathVariable Integer bookingId, @RequestParam String status) {
        log.info("Updating the status of the slot with bookingId " + bookingId);
        slotService.updateSlotStatus(bookingId, status);
        log.info("Status Updated Successfully " + status);
        return "Slot status updated successfully";
    }

    // Assign mechanic to a slot
    @PostMapping("/slots/{slotId}/assign-mechanic/{mechanicId}")
    public Slot assignMechanic(@PathVariable Integer slotId, @PathVariable Integer mechanicId) {
        log.info("Assigning Mechanics to the slot with slot Id " + slotId);
        Slot slot = slotService.assignMechanicToSlot(slotId, mechanicId);
        log.info("Assigned mechanic to the slots Successfully " + slot.getMechanic());
        return slot;
    }
}

