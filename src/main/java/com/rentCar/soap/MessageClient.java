package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.*;
import com.rentCar.dto.MessageDTO;
import com.rentCar.model.User;
import com.rentCar.service.MessageService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.ArrayList;
import java.util.List;


public class MessageClient extends WebServiceGatewaySupport {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    public PostMessageResponse postMessage(MessageDTO message) {
        Messages messageRequest = new Messages();

        messageRequest.setContent(message.getContent());
        messageRequest.setRentRequestId(message.getRentRequestId());
        messageRequest.setSenderId(message.getSenderId());

        PostMessageRequest postMessageRequest = new PostMessageRequest();
        postMessageRequest.setMessage(messageRequest);
        PostMessageResponse response = (PostMessageResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8087/ws/microservices/messages", postMessageRequest,
                        new SoapActionCallback(
                                "http://localhost:8087/ws/microservices/messages/PostMessageRequest"));

        return response;
    }

    public List<MessageDTO> getMessages(Long rentRequestId, Long userId) {


        GetMessageRequest request = new GetMessageRequest();
        request.setRentRequestId(rentRequestId);
        request.setUserId(userId);

        GetMessageResponse response = (GetMessageResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8087/ws/microservices/messages", request,
                        new SoapActionCallback(
                                "http://localhost:8087/ws/microservices/messages/GetMessageRequest"));
        List<MessageDTO> listResponse = new ArrayList<>();
        for (Messages m : response.getMessage()) {
            User sender = userService.findById(m.getSenderId());
            MessageDTO message = new MessageDTO();
            message.setContent(m.getContent());
            message.setDateString(m.getDate());
            message.setId(m.getId());
            message.setRecepientId(m.getRecepientId());
            message.setRentRequestId(m.getRentRequestId());
            message.setSeen(m.isSeen());
            message.setSenderId(m.getSenderId());
            message.setSenderUsername(sender.getUsername());
            listResponse.add(message);

        }
        return listResponse;
    }


}
