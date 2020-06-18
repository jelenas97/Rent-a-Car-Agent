package com.rentCar.soap;

import com.rentCar.RentCar.wsdl.*;
import com.rentCar.model.Advertisement;
import com.rentCar.model.FuelType;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class AdClient extends WebServiceGatewaySupport {
    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private PricelistService priceListService;

    public PostAdResponse postAd(Advertisement ad) {
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

        PostAdRequest request = new PostAdRequest();


        request.setOwnerId(3l);
        request.setStartDate(ad.getStartDate().toString());
        request.setEndDate(ad.getEndDate().toString());
        request.setCdw(ad.getCdw());
        request.setLimitKm(ad.getKilometresLimit());
        request.setLocation(ad.getPlace());


        com.rentCar.model.PriceList existingPl = priceListService.findById(ad.getPriceList().getId());

        PriceList priceList = new PriceList();

        priceList.setId(existingPl.getId());
        priceList.setCreatorId(existingPl.getCreator().getId());
        priceList.setCdw(existingPl.getCdw());
        priceList.setPricePerDay(existingPl.getPricePerDay());
        priceList.setPricePerKm(existingPl.getPricePerKm());

        request.setPriceList(priceList);


        Car car = new Car();

        CarBrand carBrandSoap = new CarBrand();
        carBrandSoap.setActive(ad.getCar().getCarBrand().getActive());
        carBrandSoap.setId(ad.getCar().getCarBrand().getId());
        carBrandSoap.setName(ad.getCar().getCarBrand().getName());
        car.setCarBrand(carBrandSoap);

        CarModel carModelSoap = new CarModel();
        carModelSoap.setActive(ad.getCar().getCarModel().getActive());
        carModelSoap.setId(ad.getCar().getCarModel().getId());
        carModelSoap.setName(ad.getCar().getCarModel().getName());
        car.setCarModel(carModelSoap);

        CarClass carClassSoap = new CarClass();
        carClassSoap.setActive(ad.getCar().getCarClass().getActive());
        carClassSoap.setId(ad.getCar().getCarClass().getId());
        carClassSoap.setName(ad.getCar().getCarClass().getName());
        car.setCarClass(carClassSoap);

        car.setKidSeats(ad.getCar().getKidSeats());
        for (FuelType type : ad.getCar().getFuelType()) {
            com.rentCar.RentCar.wsdl.FuelType fuelType = new com.rentCar.RentCar.wsdl.FuelType();
            fuelType.setActive(type.getActive());
            fuelType.setId(type.getId());
            fuelType.setName(type.getName());
            car.getFuelType().add(fuelType);
        }

        car.setAvailableTracking(ad.getCar().getAvailableTracking());
        car.setMileage(ad.getCar().getMileage());

        TransmissionType transmissionTypeSoap = new TransmissionType();
        transmissionTypeSoap.setActive(ad.getCar().getTransmissionType().getActive());
        transmissionTypeSoap.setId(ad.getCar().getTransmissionType().getId());
        transmissionTypeSoap.setName(ad.getCar().getTransmissionType().getName());
        car.setTransmissionType(transmissionTypeSoap);

        for (String image : ad.getCar().getImageGallery()) {
            car.getImageGallery().add(image);
        }

        request.setCar(car);

        PostAdResponse response = (PostAdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8084/ws/advertisement", request,
                        new SoapActionCallback(
                                "http://localhost:8084/ws/advertisement/PostAdRequest"));

        return response;
    }
}
