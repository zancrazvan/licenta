package com.licenta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.licenta.document.User;

public interface UserRepository extends MongoRepository<User, Integer> {

}
