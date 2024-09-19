package com.fiap.challenge.repository;

import com.fiap.challenge.model.DevicePreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicePreferencesRepository extends JpaRepository<DevicePreferences, Long> {
    DevicePreferences findByUserId(Long userId);
}

