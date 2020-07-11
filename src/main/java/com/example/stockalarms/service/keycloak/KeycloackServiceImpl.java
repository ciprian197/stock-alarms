package com.example.stockalarms.service.keycloak;

import com.example.stockalarms.dto.KeycloakUserDto;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
class KeycloackServiceImpl implements KeycloackService {

    private final Keycloak keycloakClient;

    private final String keycloakRealm;

    public KeycloackServiceImpl(@Value("${stock-alarms.keycloak.credentials.client-id}") final String keycloakClientId,
                                @Value("${stock-alarms.keycloak.credentials.secret}") final String keycloakSecret,
                                @Value("${keycloak.auth-server-url}") final String keycloakServerUrl,
                                @Value("${keycloak.realm}") final String keycloakRealm) {
        keycloakClient = KeycloakBuilder
                .builder()
                .serverUrl(keycloakServerUrl)
                .realm(keycloakRealm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakClientId)
                .clientSecret(keycloakSecret)
                .build();

        this.keycloakRealm = keycloakRealm;
    }

    @Override
    public String createUser(final KeycloakUserDto keycloakUserDto) {
        final CredentialRepresentation passwordCred =
                createCredentialRepresentation(keycloakUserDto.getPassword());

        final UserRepresentation user = new UserRepresentation();
        user.setUsername(keycloakUserDto.getEmail());
        user.setEmail(keycloakUserDto.getEmail());
        user.setEnabled(true);
        user.setRequiredActions(new ArrayList<>());
        user.setCredentials(List.of(passwordCred));

        final Response response;
        try {
            response = keycloakClient.realm(keycloakRealm).users().create(user);
        } catch (final Exception e) {
            throw new RuntimeException("Failed to crete account using authentication provider", e);
        }
        return parseCreateResourceResponse(response);
    }

    private String parseCreateResourceResponse(final Response response) {
        if (response.getStatus() == 409) {
            log.info("Duplicate email in authentication provider");
            throw new RuntimeException("Something went wrong!");
        }

        try {
            return CreatedResponseUtil.getCreatedId(response);
        } catch (final WebApplicationException exception) {
            throw new RuntimeException("Failed to crete account using authentication provider", exception);
        }
    }

    private CredentialRepresentation createCredentialRepresentation(final String password) {
        final CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);
        passwordCred.setTemporary(false);
        return passwordCred;
    }

}
