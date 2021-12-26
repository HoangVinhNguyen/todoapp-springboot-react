package com.todoapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.todoapp.model.entity.RefreshToken;
import com.todoapp.model.entity.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	public Optional<RefreshToken> findByToken(String token);

	@Modifying
	public int deleteByUser(User user);
}
