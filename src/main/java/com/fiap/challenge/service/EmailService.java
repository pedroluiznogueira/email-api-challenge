package com.fiap.challenge.service;

import com.fiap.challenge.model.EmailEntity;
import com.fiap.challenge.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    // Map to track the email send count for each user and their last email sent timestamp
    private final Map<Long, EmailRateLimit> userEmailRateLimitMap = new ConcurrentHashMap<>();

    // Email sending limit, e.g., 5 emails per minute
    private static final int EMAIL_LIMIT = 5;
    private static final long TIME_WINDOW_IN_SECONDS = 60;

    public List<EmailEntity> getAllEmails() {
        return emailRepository.findAll();
    }

    public EmailEntity addEmail(EmailEntity email) throws Exception {
        // Always set the userId to 0
        long userId = 0L;

        // Check if the user is exceeding the email sending limit
        if (isRateLimited(userId)) {
            throw new Exception("You have exceeded the email sending limit. Please try again later.");
        }

        // Set userId in the email and save
        email.setUserId(userId);
        updateRateLimit(userId);
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

    // Helper class to track the email sending rate for each user
    private static class EmailRateLimit {
        private int count;
        private LocalDateTime lastSentTime;

        public EmailRateLimit(int count, LocalDateTime lastSentTime) {
            this.count = count;
            this.lastSentTime = lastSentTime;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public LocalDateTime getLastSentTime() {
            return lastSentTime;
        }

        public void setLastSentTime(LocalDateTime lastSentTime) {
            this.lastSentTime = lastSentTime;
        }
    }

    // Check if the user has exceeded the rate limit
    private boolean isRateLimited(Long userId) {
        EmailRateLimit rateLimit = userEmailRateLimitMap.get(userId);
        if (rateLimit == null) {
            return false;
        }

        // Check if the time window has passed
        LocalDateTime currentTime = LocalDateTime.now();
        long timeDifference = java.time.Duration.between(rateLimit.getLastSentTime(), currentTime).getSeconds();

        if (timeDifference > TIME_WINDOW_IN_SECONDS) {
            // Reset the count if the time window has passed
            rateLimit.setCount(0);
            rateLimit.setLastSentTime(currentTime);
            return false;
        }

        // Check if the user has exceeded the email limit
        return rateLimit.getCount() >= EMAIL_LIMIT;
    }

    // Update the rate limit data for the user after successfully sending an email
    private void updateRateLimit(Long userId) {
        EmailRateLimit rateLimit = userEmailRateLimitMap.getOrDefault(userId, new EmailRateLimit(0, LocalDateTime.now()));
        rateLimit.setCount(rateLimit.getCount() + 1);
        rateLimit.setLastSentTime(LocalDateTime.now());
        userEmailRateLimitMap.put(userId, rateLimit);
    }
}
