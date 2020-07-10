package com.rentCar.service;

import com.rentCar.dto.EmailMessage;

public interface EmailService {
    void sendEmail(EmailMessage emailMessage);
}
