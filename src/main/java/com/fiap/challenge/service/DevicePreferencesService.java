package com.fiap.challenge.service;

import com.fiap.challenge.model.DevicePreferences;
import com.fiap.challenge.repository.DevicePreferencesRepository;
import org.springframework.stereotype.Service;

@Service
public class DevicePreferencesService {

    private final DevicePreferencesRepository repository;

    public DevicePreferencesService(DevicePreferencesRepository repository) {
        this.repository = repository;
    }

    public DevicePreferences getPreferences(Long userId) {
        return repository.findByUserId(userId);
    }

    public DevicePreferences savePreferences(DevicePreferences preferences) {
        return repository.save(preferences);
    }
}

