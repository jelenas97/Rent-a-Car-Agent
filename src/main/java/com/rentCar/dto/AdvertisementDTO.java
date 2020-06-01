package com.rentCar.dto;

import com.rentCar.model.Advertisement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementDTO {

    private Long id;
    private Long carId;
    private String name;
    private String carBrand;
    private String fuelType;
    private String carClass;
    private Integer kilometresLimit;
    private String transmissionType;
    private Boolean cwd;
    private Integer kidSeats;
    private Integer mileage;
    private Double rate;
    private Integer discount;
    private List<String> imageGallery;
    private Boolean availableTracking;
    private Double price;
    private String model;
    private Long ownerID;

    public AdvertisementDTO(Advertisement ad){
        this.id = ad.getId();
        this.carId = ad.getCar().getId();
        this.name = ad.getCar().getName();
        this.carBrand = ad.getCar().getCarBrand().getName();
        this.fuelType = ad.getCar().getFuelType().toString();
        this.carClass = ad.getCar().getCarClass().getName();
        this.kilometresLimit = ad.getKilometresLimit();
        this.transmissionType = ad.getCar().getTransmissionType().getName();
        this.cwd = ad.getCdw();
        this.kidSeats = ad.getCar().getKidSeats();
        this.mileage = ad.getCar().getMileage();
        this.rate = 10.0;
        this.discount = ad.getDiscount();
        this.imageGallery = ad.getCar().getImageGallery();
        this.availableTracking = ad.getCar().getAvailableTracking();
        this.price = ad.getPriceList().getPricePerDay();
        this.model = ad.getCar().getCarModel().getName();
        this.ownerID = ad.getOwner().getId();
    }

}
