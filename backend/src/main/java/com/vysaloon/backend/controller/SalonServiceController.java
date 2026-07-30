package com.vysaloon.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.vysaloon.backend.entity.SalonService;
import com.vysaloon.backend.service.SalonServiceService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/services")

@Tag(
    name = "Salon Services",
    description = "APIs for managing salon services"
)


public class SalonServiceController {

    private final SalonServiceService salonServiceService;

    public SalonServiceController(SalonServiceService salonService) {
        this.salonServiceService = salonService;
    }

    @PostMapping
    public SalonService createService(@RequestBody SalonService service) {
        return salonServiceService.createService(service);
    }

    @GetMapping
    public List<SalonService> getAllServices() {
        return salonServiceService.getAllServices();
    }

    @GetMapping("/{id}")
    public SalonService getServiceById(@PathVariable Long id) {
        return salonServiceService.getServiceById(id);
    }

    @PutMapping("/{id}")
    public SalonService updateService(@PathVariable Long id,
    @RequestBody SalonService service) {
        return salonServiceService.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        salonServiceService.deleteService(id);
    }
}