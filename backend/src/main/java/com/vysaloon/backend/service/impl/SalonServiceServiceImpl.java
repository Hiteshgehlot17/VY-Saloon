package com.vysaloon.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vysaloon.backend.entity.SalonService;
import com.vysaloon.backend.repository.SalonServiceRepository;
import com.vysaloon.backend.service.SalonServiceService;

@Service
public class SalonServiceServiceImpl implements SalonServiceService {

    private final SalonServiceRepository salonServiceRepository;

    public SalonServiceServiceImpl(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }

    @Override
    public SalonService createService(SalonService service) {
        return salonServiceRepository.save(service);
    }

    @Override
    public List<SalonService> getAllServices() {
        return salonServiceRepository.findAll();
    }

    @Override
    public SalonService getServiceById(Long id) {
    return salonServiceRepository.findById(id).orElseThrow();
}

    @Override
    public SalonService updateService(Long id, SalonService service) {

    SalonService existingService = salonServiceRepository.findById(id).orElseThrow();

    existingService.setName(service.getName());
    existingService.setDescription(service.getDescription());
    existingService.setDuration(service.getDuration());
    existingService.setPrice(service.getPrice());
    existingService.setActive(service.getActive());

    return salonServiceRepository.save(existingService);
}

    @Override
    public void deleteService(Long id) {
    salonServiceRepository.deleteById(id);
}
}