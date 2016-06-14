package com.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.licenta.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from TheUser u where u.email = :email")
	public User findByEmail(@Param("email") String email);

	@Query("select u from TheUser u where u.id = :userId")
	public User findByUserId(@Param("userId") String userId);

}
