package com.rentCar.service;

import com.rentCar.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getRentRequestMessages(long id, long userId);

    void save(MessageDTO messageDTO);
}
