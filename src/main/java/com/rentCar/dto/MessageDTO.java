package com.rentCar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    private long id;
    private String dateString;
    private String content;
    private long senderId;
    private String senderUsername;
    private long recepientId;
    private long rentRequestId;

    public MessageDTO(long id, LocalDateTime date, String content, long senderId, String senderUsername, long recepientId, long rentRequestId) {
        this.id = id;
        String[] dateTime = date.toString().split("T");
        this.dateString = dateTime[0] + " at " + dateTime[1] + "h";
        this.content = content;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.recepientId = recepientId;
        this.rentRequestId = rentRequestId;
    }
}
