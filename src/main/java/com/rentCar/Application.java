package com.rentCar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Period;

@SpringBootApplication
public class Application {
    public static void main(String[] args){

        LocalDate startDate = LocalDate.of(2017, 2, 20);
        LocalDate endDate = LocalDate.of(2017, 1, 15);

        Period period = Period.between(startDate, endDate);
        
        SpringApplication.run(Application.class,args);


    }
    public boolean isOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }
}
