package com.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.licenta.entity.DeviceDTO;

public interface DeviceDTORepository extends JpaRepository<DeviceDTO, Integer> {

}
