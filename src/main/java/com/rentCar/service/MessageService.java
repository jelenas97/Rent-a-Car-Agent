package com.rentCar.service;

import com.rentCar.model.Message;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getRentRequestMessages(long id, long userId);

    Message save(MessageDTO messageDTO);
}
