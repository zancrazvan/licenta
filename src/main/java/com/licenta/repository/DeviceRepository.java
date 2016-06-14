package com.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.licenta.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

}
