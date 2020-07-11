package com.example.stockalarms.service.keycloak;


import com.example.stockalarms.dto.KeycloakUserDto;

public interface KeycloackService {

    String createUser(final KeycloakUserDto keycloakUserDto);

}
