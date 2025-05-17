package com.victornsto.restwithspringbootandjava.services;

import com.victornsto.restwithspringbootandjava.dto.v1.security.AccountCredentialsDto;
import com.victornsto.restwithspringbootandjava.dto.v1.security.TokenDto;
import com.victornsto.restwithspringbootandjava.model.User;
import com.victornsto.restwithspringbootandjava.repository.UserRepository;
import com.victornsto.restwithspringbootandjava.secutiry.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<TokenDto> singIn(AccountCredentialsDto credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );
        User user = Optional.ofNullable(repository.findByUserName(credentials.getUsername())).orElseThrow(() -> new UsernameNotFoundException("User "+ credentials.getUsername() + " not found"));
        TokenDto tokenResponse = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRoles());
        return ResponseEntity.ok(tokenResponse);
    }

    public ResponseEntity<TokenDto> refreshToken(String username, String refreshToken) {
        Optional.ofNullable(repository.findByUserName(username)).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        TokenDto tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
        return ResponseEntity.ok(tokenResponse);
    }

    	private String generateHashedPassword(String password) {
		PasswordEncoder pbkdf2Encoder = getPbkdf2Encoder();
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder.encode(password);
	}

    private static Pbkdf2PasswordEncoder getPbkdf2Encoder() {
        return new Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
    }

    public AccountCredentialsDto create(AccountCredentialsDto user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        logger.info("Creating one new user!");
        User entity = new User();
        entity.setUserName(user.getUsername());
        entity.setFullName(user.getFullname());
        entity.setPassword(generateHashedPassword(user.getPassword()));
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);
        user.setPassword(entity.getPassword());
        repository.save(entity);
        return user;
    }
}
