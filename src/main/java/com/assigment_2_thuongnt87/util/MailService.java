package com.assigment_2_thuongnt87.util;

public interface MailService {
    void sendOrderConfirmation(String toEmail, String subject, String body);
}
