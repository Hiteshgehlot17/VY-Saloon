package com.vysaloon.backend.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vysaloon.backend.entity.Appointment;
import com.vysaloon.backend.entity.Employee;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

List<Appointment> findByEmployeeAndAppointmentDate(
Employee employee,
LocalDate appointmentDate
);

boolean existsByEmployeeAndAppointmentDateAndStartTime(
Employee employee,
LocalDate appointmentDate,
LocalTime startTime
);

List<Appointment> findByEmployeeIdAndAppointmentDate(
Long employeeId,
LocalDate appointmentDate);
}