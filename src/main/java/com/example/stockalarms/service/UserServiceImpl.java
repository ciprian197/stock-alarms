package com.example.stockalarms.service;

import com.example.stockalarms.dto.KeycloakUserDto;
import com.example.stockalarms.dto.UserDto;
import com.example.stockalarms.mapper.UserMapper;
import com.example.stockalarms.model.User;
import com.example.stockalarms.repository.UserRepository;
import com.example.stockalarms.service.keycloak.KeycloackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final KeycloackService keycloackService;
    private final AuthorizationServiceImpl authorizationService;
    private final UserMapper userMapper;

    @Transactional
    public UUID createUser(final UserDto userDto) {
        final User user = userMapper.toEntity(userDto);
        final KeycloakUserDto keycloakUserDto = userMapper.toKeycloakUserDto(userDto);
        final String keycloakId = keycloackService.createUser(keycloakUserDto);

        user.setKeycloakId(keycloakId);
        final User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public User getCurrentUser() {
        return userRepository.findByKeycloakId(authorizationService.getAuthenticatedUserKeycloakId())
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

}
