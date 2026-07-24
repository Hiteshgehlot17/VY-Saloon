package com.vysaloon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vysaloon.backend.entity.SalonService;

@Repository
public interface SalonServiceRepository extends JpaRepository<SalonService, Long> {

}