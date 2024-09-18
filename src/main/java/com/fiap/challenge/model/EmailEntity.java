package com.fiap.challenge.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "emails")
@Data
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private Boolean isImportant;

    @ElementCollection
    @CollectionTable(name = "email_tags", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "tag")
    private List<String> tags;
}
