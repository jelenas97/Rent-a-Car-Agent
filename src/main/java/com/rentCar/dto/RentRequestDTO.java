package com.rentCar.dto;

import com.rentCar.model.Comment;
import com.rentCar.model.Rate;
import com.rentCar.model.RentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RentRequestDTO {

    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String startDateString;
    private String endDateString;
    private String rentRequestStatus;
    private String cars;
    private Long advertisementId;
    private Long senderId;
    private boolean rated;
    private boolean commented;

    public RentRequestDTO(RentRequest rr) {

        this.id = rr.getId();
        String[] dateTime = rr.getStartDateTime().toString().split("T");
        this.startDateString = dateTime[0] + " at " + dateTime[1] + "h";
        this.startDateTime = rr.getStartDateTime();
        String[] dateTime1 = rr.getEndDateTime().toString().split("T");
        this.endDateString = dateTime1[0] + " at " + dateTime1[1] + "h";
        this.endDateTime = rr.getEndDateTime();
        this.rentRequestStatus = rr.getRentRequestStatus().toString();

        this.cars = rr.getAdvertisement().getCar().getCarClass().toString();
        this.advertisementId = rr.getAdvertisement().getId();
        this.senderId = rr.getSender().getId();

    }

    public RentRequestDTO(RentRequest rr, List<Rate> rates, List<Comment> comments) {

        this.id = rr.getId();
        String[] dateTime = rr.getStartDateTime().toString().split("T");
        this.startDateString = dateTime[0] + " at " + dateTime[1] + "h";
        this.startDateTime = rr.getStartDateTime();
        String[] dateTime1 = rr.getEndDateTime().toString().split("T");
        this.endDateString = dateTime1[0] + " at " + dateTime1[1] + "h";
        this.endDateTime = rr.getEndDateTime();
        this.rentRequestStatus = rr.getRentRequestStatus().toString();

        this.cars = rr.getAdvertisement().getCar().getCarClass().toString();
        this.advertisementId = rr.getAdvertisement().getId();
        this.senderId = rr.getSender().getId();

        this.rated=true;
        for(Rate r : rates){
            if(r.getRentRequest()!=null){
                if(r.getRentRequest().getId()==rr.getId()){
                        this.rated=false;
                        break;
                    }
                this.rated=true;
            }

        }

        this.commented=true;
        for(Comment c : comments){
            if(c.getRentRequest()!=null){
                if(c.getRentRequest().getId()==rr.getId()){
                    this.commented=false;
                    break;
                }
                this.commented=true;
            }

        }

    }

   /* @Override
    public String toString() {
        return "RentRequestDTO{" +
                "id=" + id +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", rentRequestStatus='" + rentRequestStatus + '\'' +
                ", cars='" + cars + '\'' +
                ", advertisementId=" + advertisementId +
                ", senderId=" + senderId +
                '}';
    }*/

    @Override
    public String toString() {
        return "RentRequestDTO{" +
                "id=" + id +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", startDateString='" + startDateString + '\'' +
                ", endDateString='" + endDateString + '\'' +
                ", rentRequestStatus='" + rentRequestStatus + '\'' +
                ", cars='" + cars + '\'' +
                ", advertisementId=" + advertisementId +
                ", senderId=" + senderId +
                ", ratedBy=" + rated +
                '}';
    }
}
