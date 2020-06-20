package com.rentCar.service.impl;

import com.rentCar.dto.CarDTO;
import com.rentCar.model.Advertisement;
import com.rentCar.model.Car;
import com.rentCar.repository.AdvertisementRepository;
import com.rentCar.repository.CarRepository;
import com.rentCar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public void add(Car car) {
        car.setName(car.getCarBrand() + " " + car.getCarModel());
        carRepository.save(car);
        saveImages(car.getImageGallery(), car);

    }

    @Override
    public CarDTO findById(String id) {
        Long carId = Long.parseLong(id);
        Car car = this.carRepository.findById(carId).orElse(null);
        car = loadImages(car);

        Advertisement a= advertisementRepository.findByCarId(carId);
        CarDTO carDTO = new CarDTO(car, a);
        return carDTO;
    }


    private Car loadImages(Car car) {
        String rootPath = System.getProperty("user.dir");
        String resourceFile = rootPath + "\\images\\" + car.getId() + ".txt";
       // LINUX String resourceFile = rootPath + "/images/" + car.getId() + ".txt";
        car.setImageGallery(new ArrayList<String>());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resourceFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                car.getImageGallery().add(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        }

        return car;
    }

    private void saveImages(List<String> imageGallery, Car car) {
        String rootPath = System.getProperty("user.dir");
        String resourceFile = rootPath + "/images/" + car.getId() + ".txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resourceFile))) {
            for (int i = 0; i < imageGallery.size(); i++) {
                bufferedWriter.write(imageGallery.get(i));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file!");
        }
    }
}
