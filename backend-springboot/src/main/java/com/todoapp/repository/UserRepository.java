package com.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todoapp.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE u.username = :username ")
	public User findUserByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.id = :id ")
	public User findUserById(@Param("id") Long id);
	
}
