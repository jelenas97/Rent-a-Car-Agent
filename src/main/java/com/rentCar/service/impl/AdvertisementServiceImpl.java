package com.rentCar.service.impl;

import com.rentCar.dto.SearchDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.CarModel;
import com.rentCar.model.FuelType;
import com.rentCar.repository.AdvertisementRepository;
import com.rentCar.service.AdvertisementService;
import com.rentCar.service.CarModelService;
import com.rentCar.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private FuelTypeService fuelTypeService;

    @Override
    public List<Advertisement> search(SearchDTO dto) {
        List<Advertisement> ads = this.advertisementRepository.findAdvertisements(dto.getPlace(), dto.getStartDate(), dto.getEndDate());

        List<Advertisement> takenAds = this.advertisementRepository.findTakenAdvertisements(dto.getPlace(), dto.getStartDate(), dto.getEndDate());
        List<Advertisement> freeAds = this.advertisementRepository.findFree(dto.getPlace(), dto.getStartDate(), dto.getEndDate());

        System.out.println("Ovo su svi: " + ads);
        for (Advertisement ad : ads) {

            System.out.println("SVI: " + ad.getId());
        }
        System.out.println("Ovo su zauzeti: " + takenAds);
        for (Advertisement ad : takenAds) {

            System.out.println("Zauzeti: " + ad.getId());
        }
        System.out.println("Ovo su slobodni: " + freeAds);
        for (Advertisement ad : freeAds) {

            System.out.println("Slobodni: " + ad.getId());
        }


        CarModel carModel = this.carModelService.findOneByName(dto.getCarModel());
        FuelType fuelType = this.fuelTypeService.findOneByName(dto.getFuelType());

        List<Predicate<Advertisement>> predicates = new ArrayList<>();

        if (dto.getLimitMileage() != null) {
            Predicate<Advertisement> byLimitMileage = ad -> ad.getKilometresLimit() >= dto.getLimitMileage();
            predicates.add(byLimitMileage);
        }
        if (dto.getDmg() != null) {
            Predicate<Advertisement> byCwd = ad -> ad.getCdw() == dto.getDmg();
            predicates.add(byCwd);
        }
        if (dto.getBrand() != null) {
            Predicate<Advertisement> byBrand = ad -> ad.getCar().getCarBrand().getName().equals(dto.getBrand());
            predicates.add(byBrand);
        }
        if (dto.getFuelType() != null) {
            Predicate<Advertisement> byFuel = ad -> ad.getCar().getFuelType().contains(fuelType);
            predicates.add(byFuel);
        }
        if (dto.getCarClass() != null) {
            Predicate<Advertisement> byClass = ad -> ad.getCar().getCarClass().getName().equals(dto.getCarClass());
            predicates.add(byClass);
        }
        if (dto.getTransmissionType() != null) {
            Predicate<Advertisement> byTrans = ad -> ad.getCar().getTransmissionType().getName().equals(dto.getTransmissionType());
            predicates.add(byTrans);
        }
        if (dto.getSeats() != null) {
            Predicate<Advertisement> byKidSeats = ad -> ad.getCar().getKidSeats().equals(dto.getSeats());
            predicates.add(byKidSeats);
        }
        if (dto.getMileage() != null) {
            Predicate<Advertisement> byMileage = ad -> ad.getCar().getMileage() <= (dto.getMileage());
            predicates.add(byMileage);
        }
        if (dto.getMaxPrice() != null) {
            Predicate<Advertisement> byMaxPrice = ad -> ad.getPriceList().getPricePerDay() <= dto.getMaxPrice();
            predicates.add(byMaxPrice);
        }
        if (dto.getMinPrice() != null) {
            Predicate<Advertisement> byMinPrice = ad -> ad.getPriceList().getPricePerDay() >= dto.getMinPrice();
            predicates.add(byMinPrice);
        }

        for (Predicate<Advertisement> predicate : predicates) {
            List<Advertisement> result = freeAds.stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
            freeAds = result;
        }

        // List<Advertisement> takenAds = this.advertisementRepository.findTakenAdvertisements(dto.getPlace(), dto.getStartDate(), dto.getEndDate());


        List<Advertisement> retAds = new ArrayList<>();
        for (Advertisement adv : freeAds) {
            if (carModel != null) {
                if (adv.getCar().getCarBrand().getCarModels().contains(carModel)) {
                    retAds.add(adv);
                }
            } else {
                retAds.add(adv);
            }
        }

        retAds = loadImages(retAds);
        return retAds;
    }

    @Override
    public Advertisement find(Long id) {

        return this.advertisementRepository.find(id);
    }

    @Override
    public List<Advertisement> findAll() {
        LocalDate today = LocalDate.now();
        List<Advertisement> ads = this.advertisementRepository.findAll(today);
        ads = loadImages(ads);
        return ads;
    }

    private List<Advertisement> loadImages(List<Advertisement> ads) {
        for (int i = 0; i < ads.size(); i++) {
            String rootPath = System.getProperty("user.dir");
            String resourceFile = rootPath + "/images/" + ads.get(i).getCar().getId() + ".txt";
            ads.get(i).getCar().setImageGallery(new ArrayList<String>());

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resourceFile))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    ads.get(i).getCar().getImageGallery().add(line);
                    line = bufferedReader.readLine();
                }
            } catch (FileNotFoundException e) {
                // Exception handling
            } catch (IOException e) {
                // Exception handling
            }
        }
        return ads;
    }

    @Override
    public void add(Advertisement ad) {
        this.advertisementRepository.save(ad);
    }

    @Override
    public List<Advertisement> findAll(Long agentID) {
        LocalDate today = LocalDate.now();
        return this.advertisementRepository.findAll(today, agentID);
    }

    @Override
    public int findAllCount(Long id) {
        return this.advertisementRepository.findAllCount(id);
    }
}
