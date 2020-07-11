package com.example.stockalarms.repository;

import com.example.stockalarms.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlarmRepository extends JpaRepository<Alarm, UUID> {

    Optional<Alarm> findByIdAndUser_KeycloakId(UUID id, String keycloakId);

}
