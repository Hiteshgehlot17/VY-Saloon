package com.vysaloon.backend.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.vysaloon.backend.dto.BookAppointmentRequest;
import com.vysaloon.backend.entity.Appointment;

public interface AppointmentService {

    Appointment bookAppointment(BookAppointmentRequest request);
    List<Appointment> getAppointments();

    List<LocalTime> getAvailableSlots(Long employeeId, LocalDate date);

    void cancelAppointment(Long appointmentId);
}