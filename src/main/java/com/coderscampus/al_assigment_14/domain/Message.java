package com.coderscampus.al_assigment_14.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long id;
    private User user;
    private String content;
    private Long channelId;
}
