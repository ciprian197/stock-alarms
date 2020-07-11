package com.example.stockalarms.service;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class AuthorizationServiceImpl {

    public String getAuthenticatedUserKeycloakId() {
        return getPrincipalFromKeycloak().getName();
    }

    private static SimpleKeycloakAccount getKeycloakAccount() {
        final Object account = getAuthentication().getDetails();
        if (account instanceof SimpleKeycloakAccount) {
            return (SimpleKeycloakAccount) account;
        }
        throw new RuntimeException("Could not extract Keycloak details from token!");
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private static Principal getPrincipalFromKeycloak() {
        return getKeycloakAccount().getPrincipal();
    }

}
