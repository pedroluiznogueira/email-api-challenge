package com.fiap.challenge.service;

import com.fiap.challenge.model.EmailEntity;
import com.fiap.challenge.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<EmailEntity> getAllEmails() {
        return emailRepository.findAll();
    }

    public EmailEntity addEmail(EmailEntity email) {
        return emailRepository.save(email);
    }

    public EmailEntity updateEmail(Long id, EmailEntity updatedEmail) {
        if (emailRepository.existsById(id)) {
            updatedEmail.setId(id);
            return emailRepository.save(updatedEmail);
        }
        return null;
    }

    public boolean deleteEmail(Long id) {
        if (emailRepository.existsById(id)) {
            emailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
