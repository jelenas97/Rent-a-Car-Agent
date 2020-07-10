package com.rentCar.service.impl;

import com.rentCar.common.ProducerRMQ;
import com.rentCar.dto.EmailMessage;
import com.rentCar.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        try {
            new ProducerRMQ(emailMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
