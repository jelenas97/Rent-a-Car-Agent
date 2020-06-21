package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.CommentRequest;
import com.rentCar.RentCar.wsdl.CommentResponse;
import com.rentCar.dto.CommentDTO;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class StatisticsClient extends WebServiceGatewaySupport {


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
}
