package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.GetAllAgentsRentedTermsRequest;
import com.rentCar.RentCar.wsdl.GetAllAgentsRentedTermsResponse;
import com.rentCar.RentCar.wsdl.PostReportRequest;
import com.rentCar.RentCar.wsdl.PostReportResponse;
import com.rentCar.dto.AdvertisementDTO;
import com.rentCar.dto.CarDTO;
import com.rentCar.dto.TermDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.RentReport;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportClient extends WebServiceGatewaySupport {

    @Autowired
    private CarService carService;

    @Autowired
    private AdvertisementService advertisementService;

    public List<TermDTO> getTerms(Long agentId) {


        GetAllAgentsRentedTermsRequest request = new GetAllAgentsRentedTermsRequest();
        request.setAgentId(agentId);


        GetAllAgentsRentedTermsResponse response = (GetAllAgentsRentedTermsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8084/ws/advertisement", request,
                        new SoapActionCallback(
                                "http://localhost:8084/ws/advertisement/GetAllAgentsRentedTermsRequest"));
        List<TermDTO> listResponse = new ArrayList<>();
        for (com.rentCar.RentCar.wsdl.TermDTO t : response.getTermDTOS()) {
            TermDTO termDTO = new TermDTO();
            termDTO.setId(t.getId());
            termDTO.setStartDate(LocalDate.parse(t.getStartDate()));
            termDTO.setEndDate(LocalDate.parse(t.getEndDate()));

            Advertisement advertisement = advertisementService.find(t.getAdvertisement().getId());
            CarDTO car = carService.findById(advertisement.getCar().getId().toString());

            AdvertisementDTO advertisementDTO = new AdvertisementDTO();
            advertisementDTO.setId(advertisement.getId());
            advertisementDTO.setCarBrand(car.getCarBrand());
            advertisementDTO.setModel(car.getCarModel());
            advertisementDTO.setName(car.getName());

            termDTO.setAdvertisement(advertisementDTO);

            listResponse.add(termDTO);
        }
        return listResponse;
    }

    public PostReportResponse save(RentReport report) {

        PostReportRequest postReportRequest = new PostReportRequest();

        postReportRequest.setDescription(report.getDescription());
        postReportRequest.setKilometers(report.getKilometers());
        postReportRequest.setAdvertisementId(report.getAdvertisement().getId());
        postReportRequest.setTermId(report.getTerm().getId());

        PostReportResponse response = (PostReportResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8084/ws/advertisement", postReportRequest,
                        new SoapActionCallback(
                                "http://localhost:8084/ws/advertisement/PostReportResponse"));

        return response;

    }
}
