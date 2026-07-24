package com.vysaloon.backend.service;

import java.util.List;

import com.vysaloon.backend.entity.SalonService;

public interface SalonServiceService {

    SalonService createService(SalonService service);

    List<SalonService> getAllServices();

    SalonService getServiceById(Long id);

    SalonService updateService(Long id, SalonService service);

    void deleteService(Long id);
}