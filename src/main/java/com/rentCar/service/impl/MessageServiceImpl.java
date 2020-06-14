package com.rentCar.service.impl;

import com.rentCar.dto.MessageDTO;
import com.rentCar.model.Message;
import com.rentCar.model.RentRequest;
import com.rentCar.repository.MessageRepository;
import com.rentCar.service.MessageService;
import com.rentCar.service.RentRequestService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private UserService userService;

    @Override
    public List<MessageDTO> getRentRequestMessages(long id, long userId) {
        List<Message> lista = this.messageRepository.getRentRequestMessages(id);
        List<MessageDTO> listaDTO = new ArrayList<MessageDTO>();
        for (Message m : lista) {
            if (m.getRecepient().getId() == userId) {
                if (m.getSeen().equals(false)) {
                    m.setSeen(true);
                    this.messageRepository.save(m);
                }
            }
            listaDTO.add(new MessageDTO(m.getId(), m.getDate(), m.getContent(), m.getSender().getId(), m.getSender().getUsername(), m.getRecepient().getId(), m.getRentRequest().getId()));
        }
        return listaDTO;
    }

    @Override
    public void save(MessageDTO messageDTO) {
        Message newMessage = new Message();
        newMessage.setContent(messageDTO.getContent());
        RentRequest rentRequest = rentRequestService.findById(messageDTO.getRentRequestId());
        newMessage.setRentRequest(rentRequest);
        newMessage.setDate(LocalDateTime.now());
        newMessage.setSeen(false);
        newMessage.setSender(userService.findById(messageDTO.getSenderId()));
        if (messageDTO.getSenderId() == rentRequest.getSender().getId()) {
            newMessage.setRecepient(rentRequest.getAdvertisement().getOwner());
        } else {
            newMessage.setRecepient(rentRequest.getSender());
        }
        this.messageRepository.save(newMessage);
    }
}
