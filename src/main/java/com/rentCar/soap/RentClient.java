package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.*;
import com.rentCar.dto.RentRequestDTO;
import com.rentCar.dto.RequestsHolderDTO;
import com.rentCar.service.MessageService;
import com.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public PhysicalRentResponse physicalRent (RentRequestDTO rentDTO){

            PhysicalRentRequest request = new PhysicalRentRequest();


            request.setAdvertisementId(rentDTO.getAdvertisementId());
            request.setStartDateString(rentDTO.getStartDateTime().toString());
            request.setEndDateString(rentDTO.getEndDateTime().toString());
            request.setStartDateTime(rentDTO.getStartDateTime().toString());
            request.setEndDateTime(rentDTO.getEndDateTime().toString());
            request.setRentRequestStatus(RentRequestStatus.PENDING);
            request.setCars(rentDTO.getCars());
            request.setSenderId(rentDTO.getSenderId());
            request.setRated(false);
            request.setCommented(false);
            request.setNumberOfUnseen(0);


            PhysicalRentResponse response = (PhysicalRentResponse) getWebServiceTemplate()
                    .marshalSendAndReceive("http://localhost:8095/ws/rent", request,
                            new SoapActionCallback(
                                    "http://localhost:8095/ws/rent/PhysicalRentRequest"));

            return response;
        }

    public List<RequestsHolderDTO> getRequestHoldersRequest(Long rentRequestId) {


        GetRequestHoldersRequest request = new GetRequestHoldersRequest();
        request.setId(rentRequestId);

        GetRequestHoldersResponse response = (GetRequestHoldersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8095/ws/microservices/rent", request,
                        new SoapActionCallback(
                                "http://localhost:8095/ws/microservices/rent/GetRequestHolderRequest"));
        List<RequestsHolderDTO> listResponse = new ArrayList<>();
        for (com.rentCar.RentCar.wsdl.RequestsHolderDTO holder : response.getRequestsHolder()) {
            RequestsHolderDTO requestsHolderDTO = new RequestsHolderDTO();
            requestsHolderDTO.setBundle(holder.isBundle());
            requestsHolderDTO.setId(holder.getId());
            Set<RentRequestDTO> list = new HashSet<>();
            for (RentRequest rr : holder.getRentRequests()) {
                RentRequestDTO rrDto = new RentRequestDTO();
                rrDto.setAdvertisementId(rr.getAdvertisementId());
                rrDto.setCars(rr.getCars());
                rrDto.setId(rr.getId());
                rrDto.setEndDateString(rr.getEndDateString());
                rrDto.setStartDateString(rr.getStartDateString());
                rrDto.setEndDateTime(LocalDateTime.parse(rr.getEndDateTime()));
                rrDto.setStartDateTime(LocalDateTime.parse(rr.getStartDateTime()));
                rrDto.setStartDateString(rr.getStartDateTime());
                rrDto.setEndDateString(rr.getEndDateTime());
                rrDto.setNumberOfUnseen(rr.getNumberOfUnseen());
                rrDto.setRentRequestStatus(rr.getRentRequestStatus());
                rrDto.setSenderId(rr.getSenderId());


                list.add(rrDto);
            }

            requestsHolderDTO.setRentRequests(list);

            listResponse.add(requestsHolderDTO);
        }
        return listResponse;
    }

    public void processRequestsBundle(String confirm, RequestsHolderDTO holderDTO) {
        ConfirmRequestHolderRequest request = new ConfirmRequestHolderRequest();
        request.setConfirm(confirm);
        com.rentCar.RentCar.wsdl.RequestsHolderDTO holder = new com.rentCar.RentCar.wsdl.RequestsHolderDTO();
        holder.setBundle(holderDTO.getBundle());
        holder.setId(holderDTO.getId());
        for (RentRequestDTO rr : holderDTO.getRentRequests()) {
            RentRequest rentRequest = new RentRequest();
            rentRequest.setAdvertisementId(rr.getAdvertisementId());
            rentRequest.setId(rr.getId());
            rentRequest.setStartDateString(rr.getStartDateTime().toString());
            rentRequest.setEndDateString(rr.getEndDateTime().toString());
            rentRequest.setStartDateTime(rr.getStartDateTime().toString());
            rentRequest.setEndDateTime(rr.getEndDateTime().toString());
            rentRequest.setRentRequestStatus(rr.getRentRequestStatus());
            rentRequest.setCars(rr.getCars());
            rentRequest.setSenderId(rr.getSenderId());
            rentRequest.setNumberOfUnseen(0);
            holder.getRentRequests().add(rentRequest);
        }
        request.setRequestsHolder(holder);
        ConfirmRequestHolderResponse response = (ConfirmRequestHolderResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8095/ws/microservices/rent", request,
                        new SoapActionCallback(
                                "http://localhost:8095/ws/microservices/rent/ConfirmRequestHolderRequest"));


    }

    public void processRequest(String confirm, RentRequestDTO rentDTO) {
        ConfirmRentRequestRequest request = new ConfirmRentRequestRequest();
        request.setConfirm(confirm);
        RentRequest rentRequest = new RentRequest();
        rentRequest.setId(rentDTO.getId());
        rentRequest.setAdvertisementId(rentDTO.getAdvertisementId());
        rentRequest.setStartDateString(rentDTO.getStartDateTime().toString());
        rentRequest.setEndDateString(rentDTO.getEndDateTime().toString());
        rentRequest.setStartDateTime(rentDTO.getStartDateTime().toString());
        rentRequest.setEndDateTime(rentDTO.getEndDateTime().toString());
        rentRequest.setRentRequestStatus(rentDTO.getRentRequestStatus());
        rentRequest.setCars(rentDTO.getCars());
        rentRequest.setSenderId(rentDTO.getSenderId());
        rentRequest.setNumberOfUnseen(0);

        request.setRequest(rentRequest);

        ConfirmRentRequestResponse response = (ConfirmRentRequestResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8095/ws/microservices/rent", request,
                        new SoapActionCallback(
                                "http://localhost:8095/ws/microservices/rent/ConfirmRentRequestRequest"));


    }
}
