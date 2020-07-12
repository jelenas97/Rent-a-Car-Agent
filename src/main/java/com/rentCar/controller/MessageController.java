package com.rentCar.controller;

import com.rentCar.RentCar.wsdl.PostMessageResponse;
import com.rentCar.dto.MessageDTO;
import com.rentCar.service.MessageService;
import com.rentCar.soap.MessageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "message")
@CrossOrigin("http://localhost:4200")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageClient messageClient;

    @GetMapping(value = "/rentRequest/{id}/user/{id2}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<List<MessageDTO>> getMessagesRentRequest(@PathVariable("id") String id, @PathVariable("id2") String id2) {

        try {
            List<MessageDTO> list = new ArrayList<>();
            list = messageClient.getMessages(Long.parseLong(id), Long.parseLong(id2));
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<?> newMessage(@RequestBody MessageDTO messageDTO) {

        try {
            PostMessageResponse message = messageClient.postMessage(messageDTO);
            if (message.getIdMessage() == 0) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //this.messageService.save(messageDTO);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
