package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.CommentRequest;
import com.rentCar.RentCar.wsdl.CommentResponse;
import com.rentCar.RentCar.wsdl.GetCommentRequest;
import com.rentCar.RentCar.wsdl.GetCommentResponse;
import com.rentCar.dto.CommentDTO;
import com.rentCar.model.User;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatisticsClient extends WebServiceGatewaySupport {

    @Autowired
    private UserService userService;

    public CommentResponse commentResponse(CommentDTO commentDTO) {


        CommentRequest request = new CommentRequest();
        request.setDate(commentDTO.getDate().toString());
        request.setDateString(commentDTO.getDateString());
        request.setContent(commentDTO.getContent());
        request.setStatus(commentDTO.getStatus());
        request.setAdvertisementId(commentDTO.getAdvertisement_id());
        request.setCommenter(commentDTO.getCommenter());
        request.setCommenterId(commentDTO.getCommenter_id());
        request.setRentRequestId(commentDTO.getRent_request_id());


        CommentResponse response = (CommentResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/statistics", request,
                        new SoapActionCallback(
                                "http://localhost:8090/ws/statistics/CommentRequest"));

        return response;
    }

    public List<CommentDTO> getComments(Long advertisementId) {


        GetCommentRequest request = new GetCommentRequest();
        request.setAdvertisementId(advertisementId);


        GetCommentResponse response = (GetCommentResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/microservices/statistics", request,
                        new SoapActionCallback(
                                "http://localhost:8090/ws/microservices/statistics/GetCommentRequest"));
        List<CommentDTO> listResponse = new ArrayList<>();
        for (com.rentCar.RentCar.wsdl.CommentDTO c : response.getComment()) {
            User sender = userService.findById(c.getCommenterId());

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(c.getId());

            LocalDate date = LocalDate.parse(c.getDate());

            commentDTO.setDate(date);
            commentDTO.setDateString(c.getDate());
            commentDTO.setContent(c.getContent());
            commentDTO.setStatus(c.getStatus());
            commentDTO.setAdvertisement_id(c.getAdvertisementId());
            commentDTO.setCommenter_id(c.getCommenterId());
            commentDTO.setCommenter(c.getCommenter());
            commentDTO.setRent_request_id(c.getRentRequestId());

            listResponse.add(commentDTO);

        }
        return listResponse;
    }
}
