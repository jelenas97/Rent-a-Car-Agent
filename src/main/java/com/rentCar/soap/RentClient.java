package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.PhysicalRentRequest;
import com.rentCar.RentCar.wsdl.PhysicalRentResponse;
import com.rentCar.RentCar.wsdl.RentRequestStatus;
import com.rentCar.dto.RentRequestDTO;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class RentClient extends WebServiceGatewaySupport {

    //    public PostRentResponse sendRent(){}
    public PhysicalRentResponse physicalRent(RentRequestDTO rentDTO) {
            /*
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail("agent@gmail.com");
            loginRequest.setPassword("agent");
            getWebServiceTemplate().setMessageSender(sender);
            LoginResponse loginResponse = (LoginResponse) getWebServiceTemplate()
                    .marshalSendAndReceive("https://localhost:8083/user-service/ws/Ad", loginRequest,
                            new SoapActionCallback(
                                    "http://www.rent-a-car.com/ad-service/soap/LoginRequest"));
    */
        //  LoginResponse loginResponse = loginClient.login();

    /*
            XMLGregorianCalendar startDate = null;
            XMLGregorianCalendar endDate = null;
            try {
                startDate =
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(adDTO.getStartDate().toString());
                endDate =
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(adDTO.getEndDate().toString());
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }*/

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
                                "http://localhost:8084/ws/advertisement/PhysicalRentRequest"));

        return response;
    }

}
