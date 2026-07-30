package com.vysaloon.backend.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vysaloon.backend.dto.BookAppointmentRequest;
import com.vysaloon.backend.entity.Appointment;
import com.vysaloon.backend.entity.Employee;
import com.vysaloon.backend.entity.SalonService;
import com.vysaloon.backend.entity.enums.AppointmentStatus;
import com.vysaloon.backend.exception.AppointmentConflictException;
import com.vysaloon.backend.repository.AppointmentRepository;
import com.vysaloon.backend.repository.EmployeeRepository;
import com.vysaloon.backend.repository.SalonServiceRepository;
import com.vysaloon.backend.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final SalonServiceRepository salonServiceRepository;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            EmployeeRepository employeeRepository,
            SalonServiceRepository salonServiceRepository) {

        this.appointmentRepository = appointmentRepository;
        this.employeeRepository = employeeRepository;
        this.salonServiceRepository = salonServiceRepository;
    }

    @Override
    public Appointment bookAppointment(BookAppointmentRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        SalonService service = salonServiceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        LocalTime endTime = request.getStartTime()
                .plusMinutes(service.getDuration());

        List<Appointment> existingAppointments =
                appointmentRepository.findByEmployeeIdAndAppointmentDate(
                        employee.getId(),
                        request.getAppointmentDate());

        for (Appointment existing : existingAppointments) {

            boolean overlaps =
                    request.getStartTime().isBefore(existing.getEndTime()) &&
                    endTime.isAfter(existing.getStartTime());

            if (overlaps) {
                throw new AppointmentConflictException(
                        "Employee is already booked during this time.");
            }
        }

        Appointment appointment = new Appointment();

        appointment.setCustomerName(request.getCustomerName());
        appointment.setCustomerPhone(request.getCustomerPhone());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(endTime);
        appointment.setEmployee(employee);
        appointment.setSalonService(service);
        appointment.setStatus(AppointmentStatus.BOOKED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<LocalTime> getAvailableSlots(Long employeeId, LocalDate date) {

        List<Appointment> appointments =
                appointmentRepository.findByEmployeeIdAndAppointmentDate(employeeId, date);

        List<LocalTime> availableSlots = new ArrayList<>();

        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(18, 0);

        while (start.isBefore(end)) {

            LocalTime slot = start;

            boolean booked = appointments.stream()
                    .anyMatch(a -> a.getStartTime().equals(slot));

            if (!booked) {
                availableSlots.add(slot);
            }

            start = start.plusMinutes(30);
        }

        return availableSlots;
    }

    @Override
    public void cancelAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CANCELLED);

        appointmentRepository.save(appointment);
    }
}