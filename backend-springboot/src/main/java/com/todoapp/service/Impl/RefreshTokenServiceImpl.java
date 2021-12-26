package com.todoapp.service.Impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.exception.TokenRefreshException;
import com.todoapp.model.entity.RefreshToken;
import com.todoapp.repository.RefreshTokenRepository;
import com.todoapp.repository.UserRepository;
import com.todoapp.security.jwt.JwtUtils;
import com.todoapp.service.RefreshTokenService;

@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {
	
	private Long refreshTokenDurationMs = 1L;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}
	
	public void updateRefreshToken(String reToken, Long userId) {
		Optional<RefreshToken> opRefreshToken = refreshTokenRepository.findByToken(reToken);
		if (opRefreshToken.isPresent()) {
			RefreshToken refreshToken = opRefreshToken.get();
			if (refreshToken.getUser().getId() == userId) {
				refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
				refreshToken = refreshTokenRepository.save(refreshToken);
			}
		}
		
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please re-signin");
		}

		return token;
	}

	@Override
	public int deleteByUserId(Long userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}

}
