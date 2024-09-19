package com.fiap.challenge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicePreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;  // User identifier for linking preferences to a user
    private String theme; // Example: "dark" or "light"
    private String primaryColor;  // Example: Hex color code like "#FF5733"
    private String labelCategory;  // Example: "Work", "Personal", etc.
}

