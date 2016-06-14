package com.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.licenta.entity.House;

public interface HouseRepository extends JpaRepository<House, Integer> {

}
