package com.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.licenta.entity.SwitchingTime;

public interface SwitchingTimeRepository extends JpaRepository<SwitchingTime, Integer> {

}
