package com.todoapp.service;

import java.util.Optional;

import com.todoapp.model.entity.RefreshToken;

public interface RefreshTokenService {

	public Optional<RefreshToken> findByToken(String token);
	public RefreshToken createRefreshToken(Long userId);
	public void updateRefreshToken(String reToken, Long userId);
	public RefreshToken verifyExpiration(RefreshToken token);
	public int deleteByUserId(Long userId);
}
