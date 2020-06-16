package com.rentCar.soap;

import com.rentCar.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class AdClient extends WebServiceGatewaySupport {
    @Autowired
    private AdvertisementService advertisementService;

//    public PostAdResponse postAd(AdvertisementDTO adDTO){
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

//        PostAdRequest request = new PostAdRequest();


    //request.setOwnerId(2);
//        request.setStartDate(startDateStr);
//        request.setEndDate(endDateStr);
//        request.setCdw(adDTO.isCdw());
//        request.setLimitKm(adDTO.getLimitKm());
//        request.setLocation(adDTO.getLocation());
//        request.setLimitKm(adDTO.getLimitKm());
//
//        PriceList priceList = new PriceList();
//
//
//        com.example.team19.model.PriceList existingPl = priceListService.findById(adDTO.getPriceList().getId());
//
//        if(existingPl.getMainId() != null){
//            priceList.setId(existingPl.getMainId());
//        }else{
//            priceList.setId(0);
//            priceList.setAlias(existingPl.getAlias());
//            priceList.setDiscount20Days(existingPl.getDiscount20Days());
//            priceList.setDiscount30Days(existingPl.getDiscount30Days());
//            priceList.setPricePerDay(existingPl.getPricePerDay());
//            priceList.setPricePerKm(existingPl.getPricePerKm());
//        }
//
//
//        request.setPriceList(priceList);
//
//
//
//        Car car = new Car();
//        System.out.println("Car BRAND: "+adDTO.getCar().getCarModel().getCarBrand().getName());
//        car.setCarBrand(adDTO.getCar().getCarModel().getCarBrand().getName());
//        car.setCarModel(adDTO.getCar().getCarModel().getName());
//        car.setCarClass(adDTO.getCar().getCarClass().name());
//        car.setChildrenSeats(adDTO.getCar().getChildrenSeats());
//        car.setFeulType(adDTO.getCar().getFuelType().name());
//        car.setHasAndroidApp(adDTO.getCar().isHasAndroidApp());
//        car.setMileage(adDTO.getCar().getMileage());
//        car.setTransType(adDTO.getCar().getTransType().name());
//
//        for(String img: adDTO.getCar().getPhotos64()) {
//            car.getPhotos64().add(img);
//        }
//
//        if(adDTO.getCar().getId() != null){
//            com.example.team19.model.Car existingCar = carService.findById(adDTO.getCar().getId());
//            if(existingCar.getMainId() != null) {
//                car.setId(existingCar.getMainId());
//            }else{
//                car.setId(0);
//            }
//
//        }else{
//            car.setId(0);
//        }
//
//
//        request.setCar(car);
//
//
//
//
//
//        PostAdResponse response = (PostAdResponse) getWebServiceTemplate()
//                .marshalSendAndReceive(request,
//                        new SoapRequestHeaderModifier(loginResponse.getToken()));
//
//
//    return response;
//    }
}
