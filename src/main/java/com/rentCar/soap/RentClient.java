package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.GetRentRequestRequest;
import com.rentCar.RentCar.wsdl.GetRentRequestResponse;
import com.rentCar.RentCar.wsdl.RentRequest;
import com.rentCar.dto.RentRequestDTO;
import com.rentCar.service.MessageService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentClient extends WebServiceGatewaySupport {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    public ResponseEntity<List<RentRequestDTO>> getRentRequests(Long rentRequestId) {


        GetRentRequestRequest request = new GetRentRequestRequest();
        request.setRentRequestId(rentRequestId);

        GetRentRequestResponse response = (GetRentRequestResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8095/ws/microservices/rent", request,
                        new SoapActionCallback(
                                "http://localhost:8095/ws/microservices/rent/GetRentRequestRequest"));
        List<RentRequestDTO> listResponse = new ArrayList<>();
        for (RentRequest rr : response.getRentRequest()) {
            RentRequestDTO rrDto = new RentRequestDTO();
            rrDto.setAdvertisementId(rr.getAdvertisementId());
            rrDto.setCars(rr.getCars());
            rrDto.setId(rr.getId());
            rrDto.setEndDateString(rr.getEndDateString());
            rrDto.setStartDateString(rr.getStartDateString());
            rrDto.setEndDateTime(LocalDateTime.parse(rr.getEndDateTime()));
            rrDto.setStartDateTime(LocalDateTime.parse(rr.getStartDateTime()));
            rrDto.setNumberOfUnseen(rr.getNumberOfUnseen());
            rrDto.setRentRequestStatus(rr.getRentRequestStatus());
            rrDto.setSenderId(rr.getSenderId());

            listResponse.add(rrDto);

        }
        ResponseEntity<List<RentRequestDTO>> responseEntity = new ResponseEntity(listResponse, HttpStatus.OK);
        return responseEntity;
    }

}
