package com.fiap.challenge.controller;

import com.fiap.challenge.model.DevicePreferences;
import com.fiap.challenge.service.DevicePreferencesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
public class DevicePreferencesController {

    private final DevicePreferencesService service;

    public DevicePreferencesController(DevicePreferencesService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DevicePreferences> getPreferences(@PathVariable Long userId) {
        DevicePreferences preferences = service.getPreferences(userId);
        if (preferences == null) {
            // Return default preferences if none exist in the database
            preferences = new DevicePreferences(0L, userId, "light", "#FFFFFF", "general");
        }
        return ResponseEntity.ok(preferences);
    }

    @PostMapping
    public DevicePreferences savePreferences(@RequestBody DevicePreferences preferences) {
        return service.savePreferences(preferences);
    }
}

