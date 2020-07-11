package com.example.stockalarms.mapper;

import com.example.stockalarms.dto.KeycloakUserDto;
import com.example.stockalarms.dto.UserDto;
import com.example.stockalarms.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(final UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName()).build();
    }

    public KeycloakUserDto toKeycloakUserDto(final UserDto userDto) {
        return KeycloakUserDto.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword()).build();
    }

}
