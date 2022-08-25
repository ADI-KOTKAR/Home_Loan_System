package com.barclays.homeloan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.homeloan.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	Optional<User> findByEmail(String email);
	
}
