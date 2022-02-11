package com.Akshay.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Akshay.Entities.UserEntity;
import com.Akshay.bindding.User;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Serializable> {
	
	public UserEntity findByUserEmail(String userEmail);

	public User save(User user);
	

	

	
}
