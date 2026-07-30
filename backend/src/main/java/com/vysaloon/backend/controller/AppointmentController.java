package com.vysaloon.backend.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vysaloon.backend.dto.BookAppointmentRequest;
import com.vysaloon.backend.entity.Appointment;
import com.vysaloon.backend.service.AppointmentService;

import jakarta.validation.Valid;
import com.vysaloon.backend.util.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/appointments")

@Tag(
    name = "Appointment Management",
    description = "APIs for booking and managing appointments"
)

public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Book Appointment
    @PostMapping("/book")
public ResponseEntity<ApiResponse<Appointment>> bookAppointment(
        @Valid @RequestBody BookAppointmentRequest request) {

    Appointment appointment = appointmentService.bookAppointment(request);

    return ResponseEntity.ok(
            ApiResponse.success(
                    "Appointment booked successfully",
                    appointment
            )
    );
}

    // Get All Appointments
    @GetMapping
    public List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }

    // Cancel Appointment
    @PutMapping("/{id}/cancel")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "Appointment cancelled successfully.";
    }

    // Get Available Slots
    @GetMapping("/available-slots")
    public List<LocalTime> getAvailableSlots(
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return appointmentService.getAvailableSlots(employeeId, date);
    }
}