package com.vysaloon.backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.vysaloon.backend.entity.enums.AppointmentStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponse {

    private Long appointmentId;

    private String customerName;

    private String customerPhone;

    private String employeeName;

    private String serviceName;

    private LocalDate appointmentDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private AppointmentStatus status;
}