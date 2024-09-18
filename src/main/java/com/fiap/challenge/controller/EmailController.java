package com.fiap.challenge.controller;

import com.fiap.challenge.model.EmailEntity;
import com.fiap.challenge.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<EmailEntity>> getAllEmails() {
        List<EmailEntity> emails = emailService.getAllEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmailEntity> addEmail(@RequestBody EmailEntity email) {
        EmailEntity savedEmail = emailService.addEmail(email);
        return new ResponseEntity<>(savedEmail, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailEntity> updateEmail(@PathVariable Long id, @RequestBody EmailEntity email) {
        EmailEntity updatedEmail = emailService.updateEmail(id, email);
        if (updatedEmail != null) {
            return new ResponseEntity<>(updatedEmail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        if (emailService.deleteEmail(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
