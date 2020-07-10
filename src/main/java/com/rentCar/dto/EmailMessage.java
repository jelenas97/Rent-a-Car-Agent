package com.rentCar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmailMessage {
    private String content;
    private String email;

    public EmailMessage(String email, String content) {
        this.email = email;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" +
                "\"content\":\"" + content + '\"' + ',' +
                "\"email\":\"" + email + '\"' +
                '}';
    }
}
