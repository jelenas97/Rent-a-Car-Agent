package com.rentCar.dto;

import com.rentCar.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private LocalDate date;
    private String dateString;
    private String content;
    private String status;
    private Long advertisement_id;
    private Long client_id;
    private String client;
    private Long rent_request_id;


    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.date = comment.getDate();
        this.dateString=comment.getDate().toString();
        this.content = comment.getContent();
        this.status = comment.getStatus().toString();
        this.advertisement_id = comment.getAdvertisement().getId();
        this.client_id = comment.getUser().getId();
        this.client= comment.getUser().getFirstName()+" "+ comment.getUser().getLastName();
        this.rent_request_id= comment.getRentRequest().getId();
    }
}
