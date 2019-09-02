package com.sda.caloriescalculator.repository;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
